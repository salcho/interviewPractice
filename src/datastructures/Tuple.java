package datastructures;

import java.util.Objects;

class Tuple<L, R> {
    final L left;
    final R right;

    Tuple(L left, R right){ this.left = left; this.right = right; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(left, tuple.left) &&
                Objects.equals(right, tuple.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
