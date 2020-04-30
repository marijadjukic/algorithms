package algorithms.chapter.advanceddatastructures;

import java.util.Set;

public class ProtoVanEmdeBoasTree {

    private int universeSize;

    private int size;

    private ProtoVanEmdeBoas protoVanEmdeBoasRoot;

    public ProtoVanEmdeBoasTree(Set<Integer> set) {
        Integer lastElement = (Integer) set.toArray()[set.size()-1];
        this.size = set.size();
        this.universeSize = lastElement + 1;
        this.protoVanEmdeBoasRoot = new ProtoVanEmdeBoas(universeSize);
    }

    public boolean isMember(Integer element) {
        return isMember(protoVanEmdeBoasRoot, element);
    }

    private boolean isMember(ProtoVanEmdeBoas proto, Integer element) {
        if (proto.getUniverseSize() == 2) {
            return proto.getElements()[element] == 1;
        }
        return isMember(proto.getCluster()[proto.getHigh(element)], proto.getLow(element));
    }


    public Integer minimum() {
        return minimum(protoVanEmdeBoasRoot);
    }

    private Integer minimum(ProtoVanEmdeBoas proto) {
        if(proto.getUniverseSize() == 2) {
            if (proto.getElements()[0]==1) {
                return 0;
            } else if (proto.getElements()[1] == 1) {
                return 1;
            } else {
                return null;
            }
        } else {
            Integer minCLuster = minimum(proto.getSummary());
            if (minCLuster == null) {
                return null;
            } else {
                Integer offset = minimum(proto.getCluster()[minCLuster]);
                return proto.getIndex(minCLuster, offset);
            }
        }
    }

    public Integer successor(Integer element) {
        return successor(protoVanEmdeBoasRoot, element);
    }

    private Integer successor(ProtoVanEmdeBoas proto, Integer element) {
        if (proto.getUniverseSize() == 2) {
            if (element == 0 && proto.getElements()[1] == 1) {
                return 1;
            } else {
                return null;
            }
        } else {
            Integer offset = successor(proto.getCluster()[proto.getHigh(element)], proto.getLow(element));
            if (offset != null) {
                return proto.getIndex(proto.getHigh(element), offset);
            } else {
                Integer successorCluster = successor(proto.getSummary(), proto.getHigh(element));
                if (successorCluster == null) {
                    return null;
                } else {
                    offset = minimum(proto.getCluster()[successorCluster]);
                    return proto.getIndex(successorCluster, offset);
                }
            }
        }
    }

    public void insert(Integer element) {
        insert(protoVanEmdeBoasRoot, element);
        this.size++;
    }

    private void insert(ProtoVanEmdeBoas proto, Integer element) {
        if(proto.getUniverseSize() == 2) {
            proto.getElements()[element] = 1;
        } else {
            insert(proto.getCluster()[proto.getHigh(element)], proto.getLow(element));
            insert(proto.getSummary(), proto.getHigh(element));
        }
    }

    public ProtoVanEmdeBoas getRoot() {
        return this.protoVanEmdeBoasRoot;
    }

    public int getUniverseSize() {
        return this.universeSize;
    }

    public ProtoVanEmdeBoas[] getCluster() {
        return this.protoVanEmdeBoasRoot.getCluster();
    }

    public int size() {
        return this.size;
    }
}
