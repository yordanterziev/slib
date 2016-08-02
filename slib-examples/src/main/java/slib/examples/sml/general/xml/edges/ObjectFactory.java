//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.06.28 um 04:32:16 PM CEST 
//


package slib.examples.sml.general.xml.edges;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the slib.examples.sml.general.xml.edges package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Edges_QNAME = new QName("http://www.example.org/EdgeSchema", "edges");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: slib.examples.sml.general.xml.edges
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Edges }
     * 
     */
    public Edges createEdges() {
        return new Edges();
    }

    /**
     * Create an instance of {@link EdgeList }
     * 
     */
    public EdgeList createEdgeList() {
        return new EdgeList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Edges }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/EdgeSchema", name = "edges")
    public JAXBElement<Edges> createEdges(Edges value) {
        return new JAXBElement<Edges>(_Edges_QNAME, Edges.class, null, value);
    }

}
