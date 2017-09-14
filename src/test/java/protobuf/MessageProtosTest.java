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
    String readDirectMessageFilePath;
    String writeDirectMessageFilePath;
    String readGroupMessageFilePath;
    String writeGroupMessageFilePath;

    @Before
    public void setUp() throws Exception {
        msg = Message.newBuilder();
        readDirectMessageFilePath = "src/test/resources/direct_message";
        writeDirectMessageFilePath = "src/test/resources/direct_log";
        readGroupMessageFilePath = "src/test/resources/group_message";
        writeGroupMessageFilePath = "src/test/resources/group_log";
    }
    
    private void generateResource(byte[] t, String path) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(t);
        out.close();
    }
    
    @Test
    public void testCreateDirectMessage() throws Exception {
        msg.setSender("Usuário de teste");
        msg.setDate("31/08/2017");
        msg.setTime("14:28");

        ByteString input = ByteString.copyFromUtf8("Minha mensagem");
        Message.Content.Builder dados =
                Message.Content.newBuilder().setData(input);
        dados.setType("text/plain");
        
        msg.addContent(dados);
        Message m = msg.build();
        
        byte[] transportData = m.toByteArray();
        generateResource(transportData, writeDirectMessageFilePath);
        assertEquals(68, transportData.length);
    }
    
    @Test
    public void testReadDirectMessage() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(readDirectMessageFilePath));
        byte[] dataReceived = (byte[]) in.readObject();
        in.close();
        Message r = Message.parseFrom(dataReceived);
        
        assertEquals("Usuário de teste", r.getSender());
        assertEquals("31/08/2017", r.getDate());
        assertEquals("14:28", r.getTime());        
        
        for (Message.Content dadosRecebidos : r.getContentList()) {
            switch (dadosRecebidos.getType()) {
                case "text/plain":
                    String saida = dadosRecebidos.getData().toStringUtf8();
                    assertEquals("Minha mensagem", saida);
                    break;

                default:
                    break;
            }
        }
    }
    
    @Test
    public void testCreateGroupMessage() throws Exception {
        msg.setSender("Usuário do grupo");
        msg.setDate("31/08/2017");
        msg.setTime("23:33");
        msg.setGroup("Turma do Pagode");

        ByteString input = ByteString.copyFromUtf8("Mensagem pra geral");
        Message.Content.Builder dados =
                Message.Content.newBuilder().setData(input);
        dados.setType("text/plain");
        
        msg.addContent(dados);
        Message m = msg.build();
        
        byte[] transportData = m.toByteArray();
        generateResource(transportData, writeGroupMessageFilePath);
        assertEquals(89, transportData.length);
    }
    
    @Test
    public void testReadGroupMessage() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(readGroupMessageFilePath));
        byte[] dataReceived = (byte[]) in.readObject();
        in.close();
        Message r = Message.parseFrom(dataReceived);
        
        assertEquals("Usuário do grupo", r.getSender());
        assertEquals("31/08/2017", r.getDate());
        assertEquals("23:33", r.getTime());
        assertEquals("Turma do Pagode", r.getGroup());
        
        for (Message.Content dadosRecebidos : r.getContentList()) {
            switch (dadosRecebidos.getType()) {
                case "text/plain":
                    String saida = dadosRecebidos.getData().toStringUtf8();
                    assertEquals("Mensagem pra geral", saida);
                    break;

                default:
                    break;
            }
        }
    }

}
