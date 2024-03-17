import org.example.manager.CustomersMGR;
import org.example.manager.MobileDevicesMGR;
import org.example.models.Customer;
import org.example.models.MobileDevice;
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
import static org.mockito.Mockito.verify;

public class MobileDevicesTest {
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private MobileDevicesMGR mobileDevicesMGR;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockScanner = mock(Scanner.class);


        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("mobile_device_id")).thenReturn(1);
        when(mockResultSet.getInt("imei_number")).thenReturn(123);
        when(mockResultSet.getString("phone_brand")).thenReturn("Apple");
        when(mockResultSet.getString("model_number")).thenReturn("12 pro");
        mobileDevicesMGR = new MobileDevicesMGR() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };
    }


    @Test
    public void testInsert() throws SQLException {
        MobileDevice mobileDevice = new MobileDevice();
        mobileDevicesMGR.insert(mobileDevice);
        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).setInt(1, mobileDevice.getImeiNumber());
        verify(mockStatement).setString(2, mobileDevice.getModelNumber());
        verify(mockStatement).setString(3, mobileDevice.getModelNumber());
        verify(mockStatement).executeUpdate();
    }
    @Test
    public void testGetAll() throws SQLException {
        List<MobileDevice> mobileDevices = mobileDevicesMGR.getAll();
        verify(mockConnection).createStatement();
        verify(mockStatement).executeQuery("SELECT * FROM mobile_devices;");

        assertEquals(1, mobileDevices.size());
        MobileDevice mobileDevice = mobileDevices.get(0);
        assertEquals(1, mobileDevice.getId());
        assertEquals(123, mobileDevice.getImeiNumber());
        assertEquals("Apple", mobileDevice.getPhonebrand());
        assertEquals("12 pro", mobileDevice.getModelNumber());
    }
    @Test
    public void testUpdateDataIntoTable() throws SQLException {
        MobileDevice testMobileDevice = new MobileDevice();
        testMobileDevice.setId(1);
        testMobileDevice.setImeiNumber(123);
        testMobileDevice.setPhonebrand("Apple");
        testMobileDevice.setModelNumber("12 pro");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        mobileDevicesMGR = new MobileDevicesMGR() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }

        };
        mobileDevicesMGR.update(testMobileDevice);
        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).setInt(1, testMobileDevice.getImeiNumber());
        verify(mockStatement).setString(2, testMobileDevice.getPhonebrand());
        verify(mockStatement).setString(3, testMobileDevice.getModelNumber());
        verify(mockStatement).setInt(4, testMobileDevice.getId());
    }
    @Test
    public void testDeleteDataFromTable() throws SQLException {
        int testModelDeviceId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        mobileDevicesMGR = new MobileDevicesMGR() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };
        mobileDevicesMGR.delete(testModelDeviceId);
        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).setInt(1, testModelDeviceId);
        verify(mockStatement).executeUpdate();
    }
}


