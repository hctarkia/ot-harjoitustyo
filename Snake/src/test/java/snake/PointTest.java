package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.domain.Point;

public class PointTest {
    
    private Point point;
    
    @Before
    public void setUp() {
        point = new Point(15, 10);
    }
    
    @Test
    public void returnsCorrectX() {
        assertEquals(point.getX(), 15);
    }
    
    @Test
    public void returnsCorrectY() {
        assertEquals(point.getY(), 10);
    }
    
    @Test
    public void equalsReturnsTrueWhenSameXAndY() {
        assertTrue(point.equals(new Point(15, 10)));
    }
    
    @Test
    public void equalsReturnsFalseWhenDifferentXAndY() {
        assertFalse(point.equals(new Point(5, 5)));
    }
    
    @Test
    public void equalsReturnsTrueWhenSameObject() {
        assertTrue(point.equals(point));
    }
}
