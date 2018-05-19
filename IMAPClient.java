package mail;

import java.util.Arrays;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.imap.SortTerm;

public class IMAPClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Properties props = new Properties();
//		props.put("mail.imap.host", "xxxx");
//		props.put("mail.imap.port", "993");
//		props.put("mail.store.protocol", "imaps");

//		props.put("mail.imap.host", "xxxx");
//		props.put("mail.imap.port", "993");
//		props.put("mail.store.protocol", "imaps");
//		props.put("mail.imap.auth", "true");
//		props.put("mail.imap.starttls.enable", "true");
		
		
//		Authenticator auth = new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("xxxxx@xxxxx", "xxxxx");
//			}
//		};
		
		
		Session session = Session.getDefaultInstance(props, null);
		
//		session.setDebug(true);
		
		IMAPStore store = (IMAPStore)session.getStore("imaps");
		
		//store.connect();
//		store.connect("imap.gmail.com", "xxxx@xxxxx", "xxxx");
		store.connect("imap.worksmobile.com", "xxxx@xxxx", "xxxx");

		System.out.println("SORT:" + store.hasCapability("SORT*"));
		
		
		//		Folder [] personalFolders = store.getPersonalNamespaces();
//		System.out.println(personalFolders.length);
//		Folder personalFolder = personalFolders[0];
//		System.out.println("fullname:" + personalFolder.getFullName());
//		
//		Folder [] sharedFolders = store.getSharedNamespaces();
//		System.out.println(sharedFolders.length);
//		Folder [] userFolders = store.getUserNamespaces("kangtae49@gmail.com");
//		System.out.println(userFolders.length);
		
		Folder defaultFolder = store.getDefaultFolder();
		
		System.out.println("getSeparator:" + defaultFolder.getSeparator());
//		Folder [] folders = defaultFolder.list("*");
		Folder [] folders = defaultFolder.list("INBOX");
		for (int i=0; i<folders.length; i++){
//			UIDFolder folder = (UIDFolder)folders[i];
			IMAPFolder folder = (IMAPFolder)folders[i];
			System.out.println("-------------------");
			
			
			System.out.println("getFullName:" + folder.getFullName());
			System.out.println("getName:" + folder.getName());
			System.out.println("getType:" + folder.getType());
			System.out.println("getSeparator:" + folder.getSeparator());
			System.out.println("getURLName:" + folder.getURLName().toString());


			
			if ( (folder.getType() & Folder.HOLDS_MESSAGES) != 0 ){
//				folder.open(Folder.READ_ONLY);
				folder.open(Folder.READ_WRITE);
				System.out.println("isOpen:" + folder.isOpen());
				if (folder.isOpen()){

					System.out.println("getPermanentFlags:" + folder.getPermanentFlags());
					Flags permanentFlag = folder.getPermanentFlags();
					System.out.println("permanentUserFlags:" + Arrays.toString(permanentFlag.getUserFlags()));
//					permanentFlag.remove(new Flags("$star"));
//					permanentFlag.remove("Star");
//					permanentFlag.remove("$star");
//					permanentFlag.remove("Star");
//					folder.getPermanentFlags().remove("Star");
					
					
					
					System.out.println("permanentUserFlags:" + Arrays.toString(permanentFlag.getUserFlags()));

					System.out.println("getMode:" + folder.getMode());
					System.out.println("getMessageCount:" + folder.getMessageCount());
					System.out.println("getNewMessageCount:" + folder.getNewMessageCount());
					System.out.println("getUnreadMessageCount:" + folder.getUnreadMessageCount());
					System.out.println("getDeletedMessageCount:" + folder.getDeletedMessageCount());
					
//					Message [] messages = folder.getMessages();
					SortTerm [] term = new SortTerm [] {SortTerm.REVERSE};
					SearchTerm sterm = new SearchTerm() {
						@Override
						public boolean match(Message msg) {
//							try {
//								String subJect = msg.getSubject();
//								if(subJect.contains(""))
//							} catch (MessagingException e) {
//								e.printStackTrace();
//							}
//							return false;
							return true;
						}
					};
					
//					Message [] messages = folder.getSortedMessages(term, sterm);
					Message [] messages = folder.getMessages();
					
					
					for(int j=0; j<messages.length; j++){
						Message message = messages[j];
						long uid = folder.getUID(message);
						System.out.println("UID:" + uid);
						if(j == 0){
							
							Flags msgFlags = message.getFlags();
							Flag [] systemFlags = msgFlags.getSystemFlags();
							String [] userFlags = msgFlags.getUserFlags();
							System.out.println("ANSWERED:" + message.isSet(Flag.ANSWERED));
							System.out.println("DELETED:" + message.isSet(Flag.DELETED));
							System.out.println("DRAFT:" + message.isSet(Flag.DRAFT));
							System.out.println("FLAGGED:" + message.isSet(Flag.FLAGGED));  // 별
							System.out.println("RECENT:" + message.isSet(Flag.RECENT));
							System.out.println("SEEN:" + message.isSet(Flag.SEEN));
							System.out.println("USER:" + message.isSet(Flag.USER));
							
							
							
							
							System.out.println("userFlags:" + Arrays.toString(userFlags));

							Flags tmp = new Flags("$star");
							message.setFlags(tmp, false);
							System.out.println("Subject:" + message.getSubject());
							break;
						}
					}
					folder.close(true);
				}
			}
			
			store.close();
//			folder.getPermanentFlags()
		}
		
		
		
	}

}
