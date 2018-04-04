package model.database;

import org.junit.jupiter.api.Test;

public class TestConnection {
    @Test
    public void JustTestConnection() {
        if(DB_Connection.getConnection() != null) {
            System.out.println("Successfulllllyyyyy");
        }
    }

    @Test
    public void testOperator() {
        int x = 1;
        int i = 1 + 2 * x++ * x;
        System.out.println(i);
    }
}
