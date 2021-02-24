package bst;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>((k1, k2) -> {
            return k2-k1;
        });
    }

    @AfterEach
    void tearDown() {
        bst = null;
    }

    @Test
    void add() {
        assertTrue(bst.add(1));
        assertTrue(bst.add(29));
        assertTrue(bst.add(20));
        assertFalse(bst.add(29));
        assertTrue(bst.add(3));
        assertTrue(bst.add(40));
        bst.printTree();
    }

    @Test
    void height() {
        bst.add(20);
        bst.add(10);
        bst.add(47);
        bst.add(30);
        assertEquals(3, bst.height());
    }

    @Test
    void size() {
        bst.add(20);
        bst.add(10);
        bst.add(47);
        bst.add(30);
        assertEquals(4, bst.size());
    }

    @Test
    void clear() {
        bst.add(20);
        bst.add(10);
        bst.add(47);
        bst.add(30);
        bst.clear();
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }
}