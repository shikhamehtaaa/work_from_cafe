var book = new CafeBook();
book.Add(new Cafe("Sample Cafe A", true, 2));
book.Add(new Cafe("Sample Cafe B", true, 4));
book.Add(new Cafe("Sample Cafe C", false, 1));

Console.WriteLine($"Total: {book.Count}");
foreach (var c in book.QuietCafes(2))
{
    Console.WriteLine(c);
}

public class Cafe
{
    public string Name { get; }
    public bool Wifi { get; }

    private int _noiseLevel;
    public int NoiseLevel
    {
        get => _noiseLevel;
        set
        {
            if (value < 1 || value > 5)
                throw new ArgumentOutOfRangeException(nameof(value), "Noise level must be 1-5");
            _noiseLevel = value;
        }
    }

    public Cafe(string name, bool wifi, int noiseLevel)
    {
        Name = name;
        Wifi = wifi;
        NoiseLevel = noiseLevel;      // goes through the setter, so validation runs
    }

    public override string ToString() =>
        $"{Name} (wifi: {(Wifi ? "yes" : "no")}, noise: {NoiseLevel}/5)";
}

public class CafeBook
{
    private readonly List<Cafe> _cafes = new();

    public void Add(Cafe cafe) => _cafes.Add(cafe);
    public int Count => _cafes.Count;

    public List<Cafe> QuietCafes(int maxNoise) =>
        _cafes.Where(c => c.NoiseLevel <= maxNoise).ToList();
}