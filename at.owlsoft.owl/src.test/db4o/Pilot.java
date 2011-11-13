package db4o;

public class Pilot
{
    private String name;
    private int    points;

    public Pilot(String name, int points)
    {
        this.name = name;
        this.points = points;
    }

    public int getPoints()
    {
        return points;
    }

    public void addPoints(int points)
    {
        this.points += points;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name + "/" + points;
    }
}
