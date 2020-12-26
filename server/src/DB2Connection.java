
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

public class DB2Connection {


    private String toJSonEvent(int id, String name, String sum, String date){
        StringBuffer sBuffer = new StringBuffer("{");
        sBuffer.append("\"Event_id\":\"" + String.valueOf(id) + "\",\n");
        sBuffer.append("\"Name\":\" " + name + "\",\n");
        sBuffer.append("\"Description\":\" " + sum + "\",\n");
        sBuffer.append("\"Date\":\"" + date + "\"\n");
        sBuffer.append("};\n");
        return sBuffer.toString();
    }
    private String toJSonUser(int id, String username, String password){
        StringBuffer sBuffer = new StringBuffer("{");
        sBuffer.append("\"id\":\"" + String.valueOf(id) + "\",\n");
        sBuffer.append("\"Username\":\" " + username+ "\",\n");
        sBuffer.append("\"Password\":\"" + password + "\"\n");
        sBuffer.append("};\n");
        return sBuffer.toString();
    }

    public void addEvent(String name, String desc ) throws SQLException {

        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM EVENTS");
        int size = 0;
        while(resultSetAccount.next()) {
            resultSetAccount.getInt(1);
            resultSetAccount.getString(2);
            resultSetAccount.getString(3);
            size++;
        }
        size+=1;
        resultSetAccount.close();

        JSONObject jsonObject = new JSONObject(name);
        System.out.println(jsonObject.getString("name"));


        String query = "INSERT INTO EVENTS (EVENT_ID, NAME, DESCRIPTION) VALUES ("+size+",'"+jsonObject.getString("name")+"', '"+jsonObject.getString("description")+"');";
        System.out.println("Add event : ");
        System.out.println(query);
        statement.execute(query);


    }


    public String showAllEvents() throws SQLException {

        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM EVENTS");
        System.out.println("List of events ");
        System.out.println("ID\tName\tDescription");
        System.out.println("==\t================\t=======");
        StringBuffer sBuffer = new StringBuffer();
        while(resultSetAccount.next()) {
            sBuffer.append(this.toJSonEvent(resultSetAccount.getInt(1), resultSetAccount.getString(2), resultSetAccount.getString(3), resultSetAccount.getString(4)));
        }
        resultSetAccount.close();
        return sBuffer.toString();
    }

    public String showEvent(int id) throws SQLException {
        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM EVENTS WHERE EVENT_ID="+ id);
        System.out.println("find account by id ");
        System.out.println("ID\tName\tSum");
        System.out.println("==\t================\t=======");
        StringBuffer sBuffer = new StringBuffer();
        while(resultSetAccount.next()) {
            sBuffer.append(this.toJSonEvent(resultSetAccount.getInt(1),
                    resultSetAccount.getString(2) ,
                    resultSetAccount.getString(3) ,
                    resultSetAccount.getString(4)));
        }
        resultSetAccount.close();
        return sBuffer.toString();
    }

    public String findUser(String s) throws SQLException {
        JSONObject jsonObject = new JSONObject(s);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        StringBuffer sBuffer = new StringBuffer();
        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM USERS WHERE USERNAME='"+ username +"' AND PASSWORD ='"+password+"'");
        System.out.println("find user by login and password ");
        while(resultSetAccount.next()) {
            sBuffer.append(this.toJSonEvent(resultSetAccount.getInt(1),
                    resultSetAccount.getString(2) ,
                    resultSetAccount.getString(3) ,
                    resultSetAccount.getString(4)));
        }
        resultSetAccount.close();
        return sBuffer.toString();
    }

    //private void addUser( int accountId, int sum) throws SQLException {
    public void addUser(String s) throws SQLException {
        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM USERS");
        int size = 0;
        while(resultSetAccount.next()) {
            resultSetAccount.getInt(1);
            resultSetAccount.getString(2);
            size++;
        }
        resultSetAccount.close();

        JSONObject jsonObject = new JSONObject(s);
        //int accountId = jsonObject.getInt("id" );
        String username = jsonObject.getString("username" );
        String password = jsonObject.getString("password" );

        size++;



        statement
                .executeUpdate(
                        "INSERT INTO USERS (ID, USERNAME , PASSWORD) VALUES ("+size+", '"+
                                username+"', '"+password+" ');");
    }



    public String showUser( int accountId) throws SQLException {
        System.out.println("show transaction for account");
        ResultSet resultSetAccount = this.statement.executeQuery("SELECT * FROM USERS WHERE ID = "+accountId+";" );
        System.out.println("ID\tac\tdesc");
        System.out.println("==\t================\t=======");

        StringBuffer sBuffer = new StringBuffer("");
        while(resultSetAccount.next()) {
            sBuffer.append(this.toJSonUser(resultSetAccount.getInt(1),
                    resultSetAccount.getString(2) ,
                    resultSetAccount.getString(3)));
        }
        resultSetAccount.close();
        return sBuffer.toString();

    }




    private  Connection connection;
    private Statement statement;

    public DB2Connection() throws SQLException{
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in"
                    + " loading or registering IBM DB2 JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            // Step 2.A: Create and
            // get connection using DriverManager class
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Courses", "root", "Vk212121");

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();
        }

            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }




    }
}
