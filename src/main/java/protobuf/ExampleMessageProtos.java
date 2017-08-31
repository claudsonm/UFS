package protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import protobuf.MessageProtos.Message;

public class ExampleMessageProtos {

    public static void main(String[] args) throws InvalidProtocolBufferException {
//        ENVIA A MENSAGEM
        Message.Builder msg = Message.newBuilder();
        msg.setSender("Usuário de teste");
        msg.setDate("31/08/2017");
        msg.setTime("14:28");

        ByteString input = ByteString.copyFromUtf8("Minha mensagem");
        Message.Content.Builder dados =
                Message.Content.newBuilder().setData(input);
        dados.setType(Message.ContentType.TEXT);
        
        msg.addContent(dados);
        Message m = msg.build();
        
        byte[] t = m.toByteArray();
        
        System.out.println(m.toByteArray() + " | " + m.getSerializedSize());
        
//        RECEBE A MENSAGEM
        Message r = Message.parseFrom(t);
        System.out.println(r.getSender());
        System.out.println(r.getDate());
        System.out.println(r.getTime());
        
        for (Message.Content dadosRecebidos : r.getContentList()) {
            switch (dadosRecebidos.getType()) {
                case TEXT:
                    String saida = dadosRecebidos.getData().toStringUtf8();
                    System.out.println("TEXT: " + saida);
                    break;

                default:
                    break;
            }
        }
    }

}
