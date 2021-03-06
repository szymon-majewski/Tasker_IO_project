package com.example.project_tasker;
import org.junit.*;
import static org.junit.Assert.*;

public class ProjectTests {
    private Project projectToTests;

    @Before
    public void setUp () {
       projectToTests = new Project();
    }

    @Test
    public void createProject(){
        assertNotNull( projectToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( projectToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( projectToTests.name, "" );
    }

    @Test
    public void projectIsEmptyAfterCreate() { assertTrue( projectToTests.categories.isEmpty() ); }

    @Test
    public void addCategory_ToEmptyProject(){
        assertTrue( projectToTests.addCategory( "firstCat", "description", 0 ) );
        assertEquals( projectToTests.categories.get( 0 ).name, "firstCat" );
    }

    @Test
    public void validation_UniqueName() {
        projectToTests.addCategory( "Unique", "desc", 0);
        assertTrue( projectToTests.validation( "otherUnique" ) );
    }

    @Test
    public void validation_NOTUniqueName() {
        projectToTests.addCategory( "notUnique", "desc", 0);
        assertFalse( projectToTests.validation( "notUnique" ) );
    }

    @Test
    public void addCategory_withColor() {
        projectToTests.addCategory( "category", "desc", 0xFFCC00);
        assertEquals( projectToTests.categories.get( projectToTests.categories.size() - 1  ).getColor(), 0xFFCC00 );

    }

    @Test
    public void addCategory_UniqueName() {
        projectToTests.addCategory( "uniqueCat", "desc", 0);
        projectToTests.addCategory( "otherUniqueCat", "desc", 0);
        assertEquals( projectToTests.categories.get( projectToTests.categories.size() - 1  ).name, "otherUniqueCat" );
    }

    @Test
    public void addCategory_NOTUniqueName() {
        projectToTests.addCategory( "notUniqueCat", "desc", 0);
        projectToTests.addCategory( "tempCat", "desc", 0);
        projectToTests.addCategory( "notUniqueCat", "desc", 0);
        assertNotEquals( projectToTests.categories.get( projectToTests.categories.size() - 1  ).name, "notUniqueCat" );
    }

    @Test
    public void deleteCategory_removeFirst() {
        projectToTests.addCategory( "firstCat", "description", 0 );

        projectToTests.deleteCategory( 0 );
        assertTrue( projectToTests.categories.isEmpty() );
    }

    @Test
    public void deleteCategory_removeFromMiddle() {
        projectToTests.addCategory( "firstCat", "description", 0 );
        projectToTests.addCategory( "secondCat", "description", 0 );
        projectToTests.addCategory( "thirdCat", "description", 0 );

        projectToTests.deleteCategory( 1 );
        assertEquals( projectToTests.categories.size(), 2 );
        assertEquals( projectToTests.categories.get( 0 ).getName(), "firstCat" );
        assertEquals( projectToTests.categories.get( 1 ).getName(), "thirdCat" );
    }

    @Test
    public void deleteCategory_removeLast() {
        projectToTests.addCategory( "firstCat", "description", 0 );
        projectToTests.addCategory( "secondCat", "description", 0 );

        projectToTests.deleteCategory( projectToTests.categories.size() - 1 );
        assertEquals( projectToTests.categories.size(), 1 );
        assertEquals( projectToTests.categories.get( 0 ).getName(), "firstCat");
    }

    @Test
    public void generateStatistics_emptyProject() {
        assertArrayEquals( new int[] {0, 0, 0, 0, 0} , projectToTests.generateStatistics() );
    }

    @Test
    public void generateStatistics_projectWithAllTasksUndone() {
        projectToTests.addCategory( "cat", "", 1 );
        projectToTests.categories.get( 0 ).addCard( "card", "" );
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task2", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task3", "");
        projectToTests.categories.get( 0 ).addCard( "card2", "" );
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task2", "");

        assertArrayEquals( new int[] {5, 2, 1, 0, 5 }, projectToTests.generateStatistics() );
    }

    @Test
    public void generateStatistics_projectWithSeveralTasksUndone() {
        projectToTests.addCategory( "cat", "", 1 );
        projectToTests.categories.get( 0 ).addCard( "card", "" );
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task2", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task3", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).tasks.get( 0 ).changeStatus();
        projectToTests.categories.get( 0 ).cards.get( 0 ).tasks.get( 1 ).changeStatus();
        projectToTests.categories.get( 0 ).addCard( "card2", "" );
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task2", "");

        assertArrayEquals( new int[] {5, 2, 1, 2, 3 }, projectToTests.generateStatistics() );
    }

    @Test
    public void generateStatistics_projectWithAllTasksDone() {
        projectToTests.addCategory( "cat", "", 1 );
        projectToTests.categories.get( 0 ).addCard( "card", "" );
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task2", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).addTask( "task3", "");
        projectToTests.categories.get( 0 ).cards.get( 0 ).tasks.get( 0 ).changeStatus();
        projectToTests.categories.get( 0 ).cards.get( 0 ).tasks.get( 1 ).changeStatus();
        projectToTests.categories.get( 0 ).cards.get( 0 ).tasks.get( 2 ).changeStatus();
        projectToTests.categories.get( 0 ).addCard( "card2", "" );
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task1", "");
        projectToTests.categories.get( 0 ).cards.get( 1 ).addTask( "task2", "");
        projectToTests.categories.get( 0 ).cards.get( 1 ).tasks.get( 0 ).changeStatus();
        projectToTests.categories.get( 0 ).cards.get( 1 ).tasks.get( 1 ).changeStatus();

        assertArrayEquals( new int[] {5, 2, 1, 5, 0 }, projectToTests.generateStatistics() );
    }

}
