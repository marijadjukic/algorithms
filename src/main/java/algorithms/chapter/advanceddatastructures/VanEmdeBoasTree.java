package algorithms.chapter.advanceddatastructures;

import java.util.Set;

public class VanEmdeBoasTree {

    private static final int DEFAULT_UNIVERSE_SIZE = 10;

    private Integer universeSize;

    private Integer min;

    private Integer max;

    private VanEmdeBoasTree summary;

    private VanEmdeBoasTree[] cluster;

    /**
     * Constructs empty van Emde Boas tree with default universe size.
     * DEFAULT_UNIVERSE_SIZE = 10
     */
    public VanEmdeBoasTree() {
        this(DEFAULT_UNIVERSE_SIZE);
    }

    /**
     * Constructs empty van Emde Boas tree with given universe size
     * @param universeSize
     */
    public VanEmdeBoasTree(int universeSize) {
        this.universeSize = universeSize;
        this.min = null;
        this.max = null;

        if(universeSize <= 2) {
            summary = null;
            cluster = new VanEmdeBoasTree[0];
        } else {
            int clusterNum = (int) Math.ceil(Math.sqrt(universeSize));

            summary = new VanEmdeBoasTree(clusterNum);
            cluster = new VanEmdeBoasTree[clusterNum];

            for (int i = 0; i < clusterNum; i++) {
                cluster[i] = new VanEmdeBoasTree((int) Math.ceil(Math.sqrt(universeSize)));
            }
        }
    }

    /**
     * Constructs van Emde Boas tree with elements from given set
     * @param set
     */
    public VanEmdeBoasTree(Set<Integer> set){
        this((Integer) set.toArray()[set.size()-1] + 1);

        for (Integer element : set) {
            insert(element);
        }
    }

    /**
     * Finds minimum element in van Emde Boas tree.
     * Operation takes constant time.
     * @return minimum element
     */
    public Integer minimum() {
        return minimum(this);
    }

    private Integer minimum(VanEmdeBoasTree vEBTree) {
        return vEBTree.min;
    }

    /**
     * Finds maximum element in van Emde Boas tree.
     * Operation takes constant time.
     * @return maximum element
     */
    public Integer maximum() {
        return maximum(this);
    }

    private Integer maximum(VanEmdeBoasTree vEBTree) {
        return vEBTree.max;
    }

    /**
     * Determines whether a value is in the set.
     * Procedure takes O(lg lg u)
     * @param element
     * @return true if element is in the van Emde Boas tree
     */
    public boolean isMember(Integer element) {
        return isMemberRecursive(this, element);
    }

    private boolean isMemberRecursive(VanEmdeBoasTree vEBTree, Integer element) {
        if (element == vEBTree.min || element == vEBTree.max) {
            return true;
        } else if (vEBTree.universeSize == 2) {
            return false;
        } else {
            return isMemberRecursive(vEBTree.cluster[high(element)],low(element));
        }
    }

    /**
     * Finds the smallest element in van Emde Boas tree greater than this element.
     * Procedure takes O(lg lg u)
     * @param element
     * @return element's successor
     */
    public Integer successor(Integer element) {
        return successorRecursive(this, element);
    }

    private Integer successorRecursive(VanEmdeBoasTree vEBTree, Integer element) {
        if (vEBTree.universeSize == 2) {
            if (element == 0 && vEBTree.max == 1) {
                return 1;
            } else {
                return null;
            }
        } else if (vEBTree.min != null && element < vEBTree.min) {
            return vEBTree.min;
        } else {
            Integer maxLow = maximum(vEBTree.cluster[vEBTree.high(element)]);
            if (maxLow != null && vEBTree.low(element) < maxLow) {
                Integer offset = successorRecursive(vEBTree.cluster[vEBTree.high(element)], vEBTree.low(element));
                return vEBTree.index(vEBTree.high(element), offset);
            } else {
                Integer successorCluster = successorRecursive(vEBTree.summary, vEBTree.high(element));
                if (successorCluster == null) {
                    return null;
                } else {
                    Integer offset = minimum(vEBTree.cluster[successorCluster]);
                    return vEBTree.index(successorCluster, offset);
                }
            }
        }
    }

    /**
     * Finds the biggest element in van Emde Boas tree less than this element.
     * Procedure takes O(lg lg u)
     * @param element
     * @return element's successor
     */
    public Integer predecessor(Integer element) {
        return predecessorRecursive(this, element);
    }

    private Integer predecessorRecursive(VanEmdeBoasTree vEBTree, Integer element) {
        if (vEBTree.universeSize == 2) {
            if (element == 1 && vEBTree.min == 0) {
                return 0;
            } else {
                return null;
            }
        } else if (vEBTree.max != null && element > vEBTree.max) {
            return vEBTree.max;
        } else {
            Integer minLow = minimum(vEBTree.cluster[vEBTree.high(element)]);
            if (minLow != null && vEBTree.low(element) > minLow) {
                Integer offset = predecessorRecursive(vEBTree.cluster[vEBTree.high(element)], vEBTree.low(element));
                return vEBTree.index(vEBTree.high(element), offset);
            } else {
                Integer predecessorCluster = predecessorRecursive(vEBTree.summary, vEBTree.high(element));
                if (predecessorCluster == null) {
                    if(vEBTree.min != null && element > vEBTree.min) {
                        return vEBTree.min;
                    } else {
                        return null;
                    }
                } else {
                    Integer offset = maximum(vEBTree.cluster[predecessorCluster]);
                    return vEBTree.index(predecessorCluster, offset);
                }
            }
        }
    }

    public void emptyInsert(VanEmdeBoasTree vEBTree, Integer element) {
        vEBTree.min = element;
        vEBTree.max = element;
    }

    /**
     * Inserts this element into van Emde Boas tree
     * Procedure takes O(lg lg u)
     * @param element
     */
    public void insert(Integer element) {
        insertRecursive(this, element);
    }

    private void insertRecursive(VanEmdeBoasTree vEBTree, Integer element) {
        if (vEBTree.min == null) {
            emptyInsert(vEBTree, element);
        } else {
            if (element < vEBTree.min) {
                Integer tmp = element;
                element = vEBTree.min;
                vEBTree.min = tmp;
            }
            if (vEBTree.universeSize > 2) {
                if (minimum(vEBTree.cluster[vEBTree.high(element)]) == null) {
                    insertRecursive(vEBTree.summary, vEBTree.high(element));
                    emptyInsert(vEBTree.cluster[vEBTree.high(element)], vEBTree.low(element));
                } else {
                    insertRecursive(vEBTree.cluster[vEBTree.high(element)], vEBTree.low(element));
                }
            }
            if (element > vEBTree.max) {
                vEBTree.max = element;
            }
        }
    }

    /**
     * Deletes this element from van Emde Boas tree
     * Procedure takes O(lg lg u)
     * @param element
     */
    public void delete(Integer element) {
        deleteRecursive(this, element);
    }

    private void deleteRecursive(VanEmdeBoasTree vEBTree, Integer element) {
        if (vEBTree.min == vEBTree.max) {
            vEBTree.min = null;
            vEBTree.max = null;
        } else if (vEBTree.universeSize == 2) {
            if (element == 0) {
                vEBTree.min = 1;
            } else {
                vEBTree.min = 0;
            }
            vEBTree.max = vEBTree.min;
        } else {
            if (element == vEBTree.min) {
                Integer firstCluster = minimum(vEBTree.summary);
                element = vEBTree.index(firstCluster, minimum(vEBTree.cluster[firstCluster]));
                vEBTree.min = element;
            }
            deleteRecursive(vEBTree.cluster[vEBTree.high(element)], vEBTree.low(element));
            if (minimum(vEBTree.cluster[vEBTree.high(element)]) == null) {
                deleteRecursive(vEBTree.summary, vEBTree.high(element));
                if (element == vEBTree.max) {
                    Integer summaryMax = maximum(vEBTree.summary);
                    if (summaryMax == null) {
                        vEBTree.max = vEBTree.min;
                    } else {
                        vEBTree.max = vEBTree.index(summaryMax, maximum(vEBTree.cluster[summaryMax]));
                    }
                }
            } else if (element == vEBTree.max) {
                vEBTree.max = vEBTree.index(vEBTree.high(element),maximum(vEBTree.cluster[vEBTree.high(element)]));
            }
        }
    }


    /**
     * Returns the number of element's cluster
     * @param element
     * @return
     */
    public int high(Integer element) {
        return element/(int)Math.ceil(Math.sqrt(universeSize));
    }

    /**
     * Returns the position of an element in the current cluster
     * @param element
     * @return
     */
    public int low(Integer element) {
        return element % (int)Math.ceil(Math.sqrt(universeSize));
    }

    /**
     * Builds an element number from x and y, treating x as the most significant of the
     * element number and y as the least significant.
     * @param x
     * @param y
     * @return
     */
    public int index(Integer x, Integer y) {
        return x*(int)Math.ceil(Math.sqrt(universeSize)) + y;
    }

}
