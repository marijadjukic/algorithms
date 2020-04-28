package algorithms.chapter.advanceddatastructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BTreeSearchItem<T> implements Serializable {

    private static final long serialVersionUID = 38978971L;

    private BTreeNode<T> node;

    private int index;

}
