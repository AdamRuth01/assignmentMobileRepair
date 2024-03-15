import org.example.manager.CustomersMGR;
import org.example.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
public class CustomerTest {
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private CustomersMGR customersMGR;
   @BeforeEach
   public void setUp() throws SQLException {
       mockConnection = mock(Connection.class);
       mockStatement = mock(PreparedStatement.class);
       mockResultSet = mock(ResultSet.class);

       when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
       when(mockConnection.createStatement()).thenReturn(mockStatement);
       when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

       when(mockResultSet.next()).thenReturn(true, false);
       when(mockResultSet.getInt("customer_id")).thenReturn(1);
       when(mockResultSet.getString("customer_name")).thenReturn("Test customer name");
       when(mockResultSet.getString("customer_phone_number")).thenReturn("Test phone number");
       when(mockResultSet.getString("customer_adress")).thenReturn("Test Adress");
       customersMGR = new CustomersMGR() {
           @Override
           protected Connection getConnection() {
               return mockConnection;
           }
       };
   }
    @Test
    public void testInsert() throws SQLException {
        Customer customer = new Customer("Test customer name","Test phone number","Test Adress");
        customersMGR.insert(customer);
        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).setString(1, customer.getName());
        verify(mockStatement).setString(2, customer.getPhoneNumber());
        verify(mockStatement).setString(3, customer.getAddress());
        verify(mockStatement).executeUpdate();
    }
    @Test
    public void testGetAll() throws SQLException {
        List<Customer> customers = customersMGR.getAll();
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM customers;");
        // Verify the result
        assertEquals(1, customers.size());
        Customer customer = customers.get(0);
        assertEquals(1, customer.getId());
        assertEquals("Test customer name", customer.getName());
        assertEquals("Test phone number", customer.getPhoneNumber());
        assertEquals("Test Adress", customer.getAddress());
    }
    @Test
    public void testUpdateDataIntoTable() throws SQLException {
        // Prepare the test data
        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("Test customer name");
        testCustomer.setPhoneNumber("Test phone number");
        testCustomer.setAddress("Test Adress");
        // Mock the Scanner
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(testCustomer.getId());
        when(mockScanner.nextLine()).thenReturn(testCustomer.getName(), testCustomer.getPhoneNumber(), testCustomer.getAddress());
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        customersMGR = new CustomersMGR() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
            @Override
            protected Scanner getScanner() {
                return mockScanner;
            }
        };
        customersMGR.updateDataIntoTable();
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).setString(1, testCustomer.getName());
        verify(mockPreparedStatement).setString(2, testCustomer.getPhoneNumber());
        verify(mockPreparedStatement).setString(3, testCustomer.getAddress());
        verify(mockPreparedStatement).setInt(4, testCustomer.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteDataFromTable() throws SQLException {
        int testCustomerId = 1;
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(testCustomerId);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        customersMGR = new CustomersMGR() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }

            @Override
            protected Scanner getScanner() {
                return mockScanner;
            }
        };
        customersMGR.deleteDataFromTable();
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).setInt(1, testCustomerId);
        verify(mockPreparedStatement).executeUpdate();
    }






}




