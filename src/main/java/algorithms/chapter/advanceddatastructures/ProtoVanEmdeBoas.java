package algorithms.chapter.advanceddatastructures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProtoVanEmdeBoas {

    private int universeSize;

    private ProtoVanEmdeBoas summary;

    private ProtoVanEmdeBoas[] cluster;

    private Integer[] elements;

    public ProtoVanEmdeBoas(int universeSize) {
        this.universeSize = universeSize;
    }

    public ProtoVanEmdeBoas(int universe, ProtoVanEmdeBoas summary, ProtoVanEmdeBoas[] cluster) {
        this.universeSize = universe;
        this.summary = summary;
        this.cluster = cluster;
    }

    public ProtoVanEmdeBoas(int universeSize, Integer[] elements) {
        this.universeSize = universeSize;
        this.elements = elements;
    }

    /**
     * Returns the number of element's cluster
     * @param element
     * @return
     */
    public int getHigh(Integer element) {
        return element/(int)Math.sqrt(universeSize);
    }

    /**
     * Returns the position of an element in the current cluster
     * @param element
     * @return
     */
    public int getLow(Integer element) {
        return element % (int)Math.sqrt(universeSize);
    }

    /**
     * Builds an element number from x and y, treating x as the most significant of the
     * element number and y as the least significant.
     * @param x
     * @param y
     * @return
     */
    public int getIndex(Integer x, Integer y) {
        return x*(int)Math.sqrt(universeSize) + y;
    }


}
