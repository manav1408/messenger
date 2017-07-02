package rbs.fitnesse;

import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class XmlMysql {
	public static void main(String args[]) throws ParserConfigurationException{  
		try{  
		/*Class.forName("com.mysql.jdbc.Driver");  
		Connection con=(Connection) DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/example","root","manav");  
		//here sonoo is database name, root is username and password  
		Statement stmt=(Statement) con.createStatement();  */
		/*ResultSet rs=stmt.executeQuery("select * from emp");  
		while(rs.next())  
		System.out.println(rs.getInt(1));  */
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse("C:/Users/Manav Middha/Desktop/book.xml");
		NodeList bookList=doc.getElementsByTagName("book");
		
		for (int i=0;i<bookList.getLength();i++)
			{
			Node p=bookList.item(i);
			if(p.getNodeType()==Node.ELEMENT_NODE)
			{
				Element person=(Element) p;
				String id=person.getAttribute("id");
				NodeList nameList=person.getChildNodes();
				for(int j=0;j<nameList.getLength();j++)
				{
					Node n=nameList.item(j);
					
					/*if(n.getNodeType()==Node.ELEMENT_NODE)
					{
						Element name=(Element) n;
						
						//System.out.println("Person" + id + ":" + name.getTagName() + "=" + name.getTextContent());
					}*/
				}
			}
				}
		/*con.close();  */
		}catch(Exception e){ System.out.println(e);}
	}
}
