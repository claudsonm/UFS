package protobuf;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.google.protobuf.ByteString;

import protobuf.MessageProtos.Message;

public class MessageProtosTest {
    Message.Builder msg;
    String readFilePath;
    String writeFilePath;

    @Before
    public void setUp() throws Exception {
        msg = Message.newBuilder();
        readFilePath = "src/test/resources/dummy_data";
        writeFilePath = "src/test/resources/data_log";
    } 
    
    @Test
    public void testCreateMessage() throws Exception {
        msg.setSender("Usuário de teste");
        msg.setDate("31/08/2017");
        msg.setTime("14:28");

        ByteString input = ByteString.copyFromUtf8("Minha mensagem");
        Message.Content.Builder dados =
                Message.Content.newBuilder().setData(input);
        dados.setType(Message.ContentType.TEXT);
        
        msg.addContent(dados);
        Message m = msg.build();
        
        byte[] transportData = m.toByteArray();
        generateResource(transportData);
        assertEquals(56, transportData.length);
    }
    
    private void generateResource(byte[] t) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(writeFilePath));
        out.writeObject(t);
        out.close();
    }
    
    @Test
    public void testReadMessage() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(readFilePath));
        byte[] dataReceived = (byte[]) in.readObject();
        in.close();
        Message r = Message.parseFrom(dataReceived);
        
        assertEquals("Usuário de teste", r.getSender());
        assertEquals("31/08/2017", r.getDate());
        assertEquals("14:28", r.getTime());        
        
        for (Message.Content dadosRecebidos : r.getContentList()) {
            switch (dadosRecebidos.getType()) {
                case TEXT:
                    String saida = dadosRecebidos.getData().toStringUtf8();
                    assertEquals("Minha mensagem", saida);
                    break;

                default:
                    break;
            }
        }
    }

}
