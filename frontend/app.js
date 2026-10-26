const JAVA_API = "http://localhost:8080/api/cafes";

class CafeApi {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
  }

  async search(filters) {
    const params = new URLSearchParams();
    for (const [key, value] of Object.entries(filters)) {
      if (value !== "" && value !== false) params.append(key, value);
    }
    const res = await fetch(`${this.baseUrl}?${params}`);
    if (!res.ok) throw new Error(`Request failed: ${res.status}`);
    return res.json();
  }
}

class CafeList {
  constructor(container) {
    this.container = container;
  }

  render(cafes) {
    this.container.replaceChildren();
    if (cafes.length === 0) {
      this.container.textContent = "No cafes match those filters.";
      return;
    }
    for (const cafe of cafes) {
      const card = document.createElement("article");
      card.className = "card";

      const title = document.createElement("h2");
      title.textContent = cafe.name;          // textContent, not innerHTML (avoids XSS)

      const details = document.createElement("p");
      details.textContent =
        `${cafe.neighborhood} · Wi-Fi: ${cafe.wifi ? "yes" : "no"} · ` +
        `Outlets: ${cafe.outlets} · Noise: ${cafe.noiseLevel}/5`;

      const notes = document.createElement("p");
      notes.textContent = cafe.notes;

      card.append(title, details, notes);
      this.container.append(card);
    }
  }
}

const api = new CafeApi(JAVA_API);
const list = new CafeList(document.getElementById("results"));

document.getElementById("filters").addEventListener("submit", async (event) => {
  event.preventDefault();
  try {
    const cafes = await api.search({
      neighborhood: document.getElementById("neighborhood").value.trim(),
      wifi: document.getElementById("wifi").checked,
      minOutlets: document.getElementById("minOutlets").value,
      maxNoise: document.getElementById("maxNoise").value,
    });
    list.render(cafes);
  } catch (err) {
    list.container.textContent = `Something went wrong: ${err.message}`;
  }
});