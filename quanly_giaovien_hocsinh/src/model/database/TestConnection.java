package model.database;

import org.junit.jupiter.api.Test;

public class TestConnection {
    @Test
    public void JustTestConnection() {
        if(DB_Connection.getConnection() != null) {
            System.out.println("Successfulllllyyyyy");
        }
    }
}
