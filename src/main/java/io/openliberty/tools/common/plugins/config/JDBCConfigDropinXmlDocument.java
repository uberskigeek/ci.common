package io.openliberty.tools.common.plugins.config;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Comment;
import org.w3c.dom.Element;

public class JDBCConfigDropinXmlDocument extends XmlDocument {
	
	   private JDBCConfigDropinXmlDocument() {    
	    }
	    
	    public static JDBCConfigDropinXmlDocument newInstance() throws ParserConfigurationException {
	        JDBCConfigDropinXmlDocument configDocument = new JDBCConfigDropinXmlDocument();
	        configDocument.createDocument("jdbc-driver");
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
        

}
