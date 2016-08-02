//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.06.28 um 04:32:16 PM CEST 
//


package slib.examples.sml.general.xml.edges;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für edges complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="edges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="horizontalEdges" type="{http://www.example.org/EdgeSchema}edgeList"/>
 *         &lt;element name="upwardEdges" type="{http://www.example.org/EdgeSchema}edgeList"/>
 *         &lt;element name="downwardEdges" type="{http://www.example.org/EdgeSchema}edgeList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "edges", propOrder = {
    "horizontalEdges",
    "upwardEdges",
    "downwardEdges"
})
public class Edges {

    @XmlElement(required = true)
    protected EdgeList horizontalEdges;
    @XmlElement(required = true)
    protected EdgeList upwardEdges;
    @XmlElement(required = true)
    protected EdgeList downwardEdges;

    /**
     * Ruft den Wert der horizontalEdges-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EdgeList }
     *     
     */
    public EdgeList getHorizontalEdges() {
        return horizontalEdges;
    }

    /**
     * Legt den Wert der horizontalEdges-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EdgeList }
     *     
     */
    public void setHorizontalEdges(EdgeList value) {
        this.horizontalEdges = value;
    }

    /**
     * Ruft den Wert der upwardEdges-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EdgeList }
     *     
     */
    public EdgeList getUpwardEdges() {
        return upwardEdges;
    }

    /**
     * Legt den Wert der upwardEdges-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EdgeList }
     *     
     */
    public void setUpwardEdges(EdgeList value) {
        this.upwardEdges = value;
    }

    /**
     * Ruft den Wert der downwardEdges-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EdgeList }
     *     
     */
    public EdgeList getDownwardEdges() {
        return downwardEdges;
    }

    /**
     * Legt den Wert der downwardEdges-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EdgeList }
     *     
     */
    public void setDownwardEdges(EdgeList value) {
        this.downwardEdges = value;
    }

}
