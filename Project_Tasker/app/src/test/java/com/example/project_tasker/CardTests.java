package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CardTests {
    private Card cardToTests;

    @Before
    public void setUp () {
        cardToTests = new Card();
    }

    @Test
    public void createCard(){
        assertNotNull( cardToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( cardToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( cardToTests.name, "" );
    }

    @Test
    public void cardIsEmptyAfterCreate() { assertTrue( cardToTests.tasks.isEmpty() ); }

    @Test
    public void addTask_ToEmptyCard(){
        assertTrue( cardToTests.addTask( "firstTask", "description" ) );
        assertEquals( cardToTests.tasks.get( 0 ).name, "firstTask" );
    }

    @Test
    public void validation_UniqueName() {
        cardToTests.addTask( "Unique", "desc");
        assertTrue( cardToTests.validation( "otherUnique" ) );
    }

    @Test
    public void validation_NOTUniqueName() {
        cardToTests.addTask( "notUnique", "desc");
        assertFalse( cardToTests.validation( "notUnique" ) );
    }

    @Test
    public void addTask_UniqueName() {
        cardToTests.addTask( "uniqueTask", "desc");
        cardToTests.addTask( "otherUniqueTask", "desc");
        assertEquals( cardToTests.tasks.get( cardToTests.tasks.size() - 1  ).name, "otherUniqueTask" );
    }

    @Test
    public void addTask_NOTUniqueName() {
        cardToTests.addTask( "notUniqueTask", "desc");
        cardToTests.addTask( "tempTask", "desc");
        cardToTests.addTask( "notUniqueTask", "desc");
        assertNotEquals( cardToTests.tasks.get( cardToTests.tasks.size() - 1  ).name, "notUniqueTask" );
    }

    @Test
    public void deleteTask_removeFirst() {
        cardToTests.addTask( "firstTask", "description" );

        cardToTests.deleteTask( 0 );
        assertTrue( cardToTests.tasks.isEmpty() );
    }

    @Test
    public void deleteTask_removeFromMiddle() {
        cardToTests.addTask( "firstTask", "description" );
        cardToTests.addTask( "secondTask", "description" );
        cardToTests.addTask( "thirdTask", "description" );

        cardToTests.deleteTask( 1 );
        assertEquals( cardToTests.tasks.size(), 2 );
        assertEquals( cardToTests.tasks.get( 0 ).getName(), "firstTask" );
        assertEquals( cardToTests.tasks.get( 1 ).getName(), "thirdTask" );
    }

    @Test
    public void deleteTask_removeLast() {
        cardToTests.addTask( "firstTask", "description" );
        cardToTests.addTask( "secondTask", "description" );

        cardToTests.deleteTask( cardToTests.tasks.size() - 1 );
        assertEquals( cardToTests.tasks.size(), 1 );
        assertEquals( cardToTests.tasks.get( 0 ).getName(), "firstTask");
    }

}
