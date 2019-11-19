package main.java.com.mitsko.unit2.entity;

public class CubeRegistration {
    private int volume;
    private int square;

    public CubeRegistration(int volume, int square) {
        this.volume = volume;
        this.square = square;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeRegistration that = (CubeRegistration) o;
        return volume == that.volume &&
                square == that.square;
    }

    @Override
    public int hashCode() {
        return 31 * ((31 * volume) + square);
    }

    @Override
    public String toString() {
        return "CubeRegistration{" +
                "volume=" + volume +
                ", square=" + square +
                '}';
    }
}
