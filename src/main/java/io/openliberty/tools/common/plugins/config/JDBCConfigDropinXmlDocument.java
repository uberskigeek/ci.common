package io.openliberty.tools.common.plugins.config;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Comment;
import org.w3c.dom.Element;

public class JDBCConfigDropinXmlDocument extends XmlDocument {
    
	   public static final String LIBRARY_REF = "libraryRef";
       public static final String LIBRARY = "library";
       public static final String FILESET = "fileset";
       public static final String JDBC_DRIVER_1 = "JdbcDriver1";
       public static final String JDBC_LIBRARY_1 = "Library1";
       public static String DRIVER_JAR = "driverJar";
       public static String JDBC_DRIVER = "jdbcDriver";
       public static String JDBC_DRIVER_ID = "JdbcDriver1";

	   private JDBCConfigDropinXmlDocument() {    
	    }
	    
	    public static JDBCConfigDropinXmlDocument newInstance() throws ParserConfigurationException {
	        JDBCConfigDropinXmlDocument configDocument = new JDBCConfigDropinXmlDocument();
	        configDocument.createDocument("server");
	        return configDocument;
	    }

	    public void createComment(String comment) {
	        createComment(doc.getDocumentElement(), comment);
	    }

	    public void createComment(Element elem, String comment) {
	        Comment commentElement = doc.createComment(comment);
	        elem.appendChild(commentElement);
	    }

	    public void createVariableWithValue(String varName, String varValue, boolean isDefaultValue) {
	        createVariableWithValue(doc.getDocumentElement(), varName, varValue, isDefaultValue);
	    }
	    
	    public void createVariableWithValue(Element elem, String varName, String varValue, boolean isDefaultValue) {
	        if (varValue == null) {
	            return;
	        }
	        Element child = doc.createElement("variable");
	        child.setAttribute("name", varName);
	        String valueAttr = isDefaultValue ? "defaultValue" : "value";
	        child.setAttribute(valueAttr, varValue);
	        elem.appendChild(child);
	    }
        public Element createElement(String elementName) {
        	   return doc.createElement(elementName);
        }
        
        public void appendChild(Element child) {
        	   doc.appendChild(child);        	
        }
        
        public void appendChildToRoot(Element child) {
        	     Element root = doc.getDocumentElement();
        	     root.appendChild(child);
        }
        
       	public void addLibrary(Map<String, String> driverInfo) throws Exception {
    		    // Add library element for JDBC jar location. 
            Element lib = createElement(LIBRARY);
            lib.setAttribute("id", JDBC_LIBRARY_1);
            Element fileLoc = createElement(FILESET);
            fileLoc.setAttribute("dir", "resources");
            fileLoc.setAttribute("includes", driverInfo.get(DRIVER_JAR));
            lib.appendChild(fileLoc);
            Element jdbcDriver = createElement(JDBC_DRIVER);
            jdbcDriver.setAttribute("id", JDBC_DRIVER_ID);
            jdbcDriver.setAttribute("libraryRef", JDBC_LIBRARY_1);
            appendChildToRoot(lib);
            appendChildToRoot(jdbcDriver);
    	    }
        

}
