package Project;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("🔍 Testing Database Connection...");
        
        try {
            // Test basic connection
            Connection con = ConnectionProvider.getCon();
            if (con != null) {
                System.out.println("✅ Connection successful!");
                
                // Test if database exists
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SHOW DATABASES LIKE 'library'");
                if (rs.next()) {
                    System.out.println("✅ Library database exists!");
                    
                    // Test if tables exist
                    rs = st.executeQuery("SHOW TABLES IN library");
                    System.out.println("📊 Tables in library database:");
                    boolean hasTables = false;
                    while (rs.next()) {
                        System.out.println("   - " + rs.getString(1));
                        hasTables = true;
                    }
                    if (!hasTables) {
                        System.out.println("❌ No tables found in library database");
                    }
                    
                } else {
                    System.out.println("❌ Library database doesn't exist!");
                }
                
                con.close();
            } else {
                System.out.println("❌ Connection failed - returned null");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}