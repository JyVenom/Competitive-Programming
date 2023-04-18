import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class P15_5 {
    private static final String NEXT_ELEMENT = "NEXT_ELEMENT";

    private static StringBuffer buf = new StringBuffer();
    private static int indentLevel = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob15.in.txt"));
        String inLine;

        while ((inLine = br.readLine()) != null) {
            buf.append(inLine);
        }

        String xml = buf.toString();
        buf = new StringBuffer();

        // print the declaration and chop it off the front
        String declarationString = xml.substring(xml.indexOf("<?xml"), (xml.indexOf("?>") + "?>".length()));
        buf.append(declarationString).append("\n");
        xml = xml.substring(xml.indexOf("?>") + "?>".length());

        // find the start of the root element
        xml = xml.substring(xml.indexOf("<"));
        XMLElement rootElement = null;
        XMLElement currentElement = null;

        // continue consuming until all start tags are gone
        while (xml.indexOf("<") == 0) {
            int nextEndTagIndex = xml.indexOf(">");
            int nextSelfClosingTagIndex = xml.indexOf("/>");
            int endIndex;

            if (nextSelfClosingTagIndex == (nextEndTagIndex - 1)) {
                // this is a self closing tag - create a new element
                XMLElement newElement = new XMLElement();

                // consume the entire element text
                endIndex = nextSelfClosingTagIndex + "/>".length();
                newElement.addToken(xml.substring(0, endIndex));

                // set the parent and add to the current element
                newElement.setParent(currentElement);
                assert currentElement != null;
                currentElement.addToken(newElement);
            } else {
                // this is a start or an end tag - find out which
                endIndex = nextEndTagIndex + ">".length();

                if (xml.startsWith("</")) {
                    // end tag - just consume it and go up a level
                    assert currentElement != null;
                    currentElement.addToken(xml.substring(0, endIndex));
                    currentElement = currentElement.getParent();
                } else {
                    // start tag - create a new element and go down a level
                    XMLElement newElement = new XMLElement();
                    if (rootElement != null) {
                        newElement.setParent(currentElement);
                        currentElement.addToken(newElement);
                    } else {
                        rootElement = newElement;
                    }
                    currentElement = newElement;
                    currentElement.addToken(xml.substring(0, endIndex));
                }
            }

            if (xml.length() > endIndex) {
                xml = xml.substring(endIndex);

                // consume anything after the next end tag and the next start tag after that
                if (xml.indexOf("<") != 0) {
                    endIndex = xml.indexOf("<");
                    currentElement.addToken(xml.substring(0, endIndex));
                    if (xml.length() > endIndex) {
                        xml = xml.substring(endIndex);
                    } else {
                        xml = xml.substring(endIndex - 1);
                    }
                }
            } else {
                xml = xml.substring(endIndex - 1);
            }
        }

        assert rootElement != null;
        rootElement.render();
        System.out.print(buf.toString());

        br.close();
    }

    private static void indent() {
        buf.append(".".repeat(4).repeat(Math.max(0, indentLevel)));
    }

    public static class XMLElement {
        private final ArrayList<Object> tokens = new ArrayList<>();
        private XMLElement parent = null;

        public void addToken(Object theObject) {
            tokens.add(theObject);
        }

        public XMLElement getParent() {
            return parent;
        }

        public void setParent(XMLElement theParent) {
            parent = theParent;
        }

        public void render() {
            // make a big string out of the element, marking the places of child elements
            StringBuilder elementBuf = new StringBuilder();
            ArrayList<XMLElement> childElements = new ArrayList<>();
            int currentChildIndex = 0;
            for (Object currentObject : tokens) {
                if (currentObject instanceof XMLElement) {
                    childElements.add((XMLElement) currentObject);
                    elementBuf.append(NEXT_ELEMENT);
                } else {
                    elementBuf.append(currentObject);
                }
            }
            StringBuilder elementString = new StringBuilder(elementBuf.toString());

            // kill whitespace between nested elements
            String[] tokens = elementString.toString().split(NEXT_ELEMENT);
            if (tokens.length > 1) {
                elementString = new StringBuilder();
                for (int i = 0; i < tokens.length; i++) {
                    if (i > 0) {
                        elementString.append(NEXT_ELEMENT);
                    }
                    elementString.append(tokens[i].trim());
                }
            }

            // print the start tag - assume the cursor is already in the right place
            int endIndex = elementString.indexOf(">") + ">".length();
            String tagString = elementString.substring(0, endIndex);
            renderTag(tagString);

            // find out if there is more to do
            if (elementString.length() > endIndex) {
                // there is more to process, meaning this was not a self closing tag - chew up what we just used
                elementString = new StringBuilder(elementString.substring(endIndex));

                // find out if there are nested tags
                if (elementString.indexOf(NEXT_ELEMENT) >= 0) {
                    // nested content - increase the indent
                    indentLevel++;
                    newLine();

                    int nextStartTagIndex = elementString.indexOf("<");
                    while (nextStartTagIndex != 0) {
                        indent();

                        // either we have a nested element next or we have content
                        if (elementString.toString().startsWith(NEXT_ELEMENT)) {
                            // another element is next
                            elementString = new StringBuilder(elementString.substring(NEXT_ELEMENT.length()));
                            childElements.get(currentChildIndex).render();
                            currentChildIndex++;
                        } else {
                            // content - either there is another tag or there isn't
                            if (elementString.indexOf(NEXT_ELEMENT) == -1) {
                                // no more elements - go until the start of the next tag
                                nextStartTagIndex = elementString.indexOf("<");
                            } else {
                                // get the content between here and the next nested element
                                nextStartTagIndex = elementString.indexOf(NEXT_ELEMENT);
                            }

                            String contentString = elementString.substring(0, nextStartTagIndex);
                            renderContent(contentString);
                            elementString = new StringBuilder(elementString.substring(nextStartTagIndex));

                            // need a new line if the next thing is not the end tag
                            //                                newLine();
                            newLine();
                        }

                        // find the next start tag
                        nextStartTagIndex = elementString.indexOf("<");
                    }

                    // now we should just have the end tag
                    indentLevel--;
                    indent();
                } else {
                    // no nested content - render the text content
                    int nextStartTagIndex = elementString.indexOf("<");
                    String contentString = elementString.substring(0, nextStartTagIndex);
                    renderContent(contentString);
                    elementString = new StringBuilder(elementString.substring(nextStartTagIndex));

                    // render the end tag

                }
                renderTag(elementString.toString());
            }  // this was a self closing tag - go to the next line

            newLine();
        }

        private void renderTag(String tagString) {
            int index = 0;

            char space = ' ';
            char lessThan = '<';
            char greaterThan = '>';
            char forwardSlash = '/';
            char equalSign = '=';

            // first print the <
            while (tagString.charAt(index) != lessThan) {
                index++;
            }
            buf.append(tagString.charAt(index));
            index++;
            tagString = tagString.substring(index);

            // see if this is an ending tag - if so, add the /
            index = 0;
            if (tagString.charAt(index) == forwardSlash) {
                buf.append(tagString.charAt(index));
                index++;
                tagString = tagString.substring(index);
            }

            // now skip spaces and add the element name
            index = 0;
            while (tagString.charAt(index) == space) {
                index++;
            }
            while ((tagString.charAt(index) != space) && (tagString.charAt(index) != greaterThan)) {
                buf.append(tagString.charAt(index));
                index++;
            }
            tagString = tagString.substring(index);

            // find out if there are attributes to worry about
            if (tagString.contains("\"")) {
                // there are attributes - deal with them
                String[] stringArray = tagString.split("\"");
                for (int i = 0; i < stringArray.length - 1; i += 2) {
                    // space before attribute name
                    buf.append(space);

                    // attribute name
                    String tempString = stringArray[i];
                    index = 0;

                    // skip spaces before name
                    while (tempString.charAt(index) == space) {
                        index++;
                    }
                    while ((tempString.charAt(index) != space) && (tempString.charAt(index) != equalSign)) {
                        buf.append(Character.toLowerCase(tempString.charAt(index)));
                        index++;
                    }

                    // equal sign and quote
                    buf.append("=\"");

                    // value as is
                    buf.append(stringArray[i + 1]);

                    // end quote
                    buf.append("\"");
                }

                tagString = stringArray[stringArray.length - 1];
            }

            // at this point there are no attributes - find out if this is self closing or not
            if (tagString.contains("/>")) {
                buf.append(space);
            }

            //ignore the rest of the spaces and print the ending sequence
            index = 0;
            while (tagString.charAt(index) == space) {
                index++;
            }
            buf.append(tagString.substring(index));
        }

        private void renderContent(String contentString) {
            contentString = contentString.trim();
            String[] stringArray = contentString.split("\\s+");
            for (int i = 0; i < stringArray.length; i++) {
                if (i > 0) {
                    buf.append(" ");
                }
                buf.append(stringArray[i]);
            }
        }

        private void newLine() {
            buf.append("\n");
        }
    }
}