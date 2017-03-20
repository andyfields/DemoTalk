package com.outlands.cooltalk.ctBusiness.services.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctCommon.constants.OLEmailMessageConstants;
import com.outlands.cooltalk.ctCommon.properties.MailPropertiesService;
import com.outlands.cooltalk.ctCommon.services.MessageService;
import com.outlands.cooltalk.ctCommon.services.MessageService.EMailMessageBean;
import com.outlands.cooltalk.ctDatabase.service.TEmailLogDataService;
import com.outlands.cooltalk.ctEntities.entity.TEmailLog;
import com.outlands.cooltalk.utility.OLDateUtils;

	
/**
 * Send mail using mailgun.
 * 
 * @author andrewfields
 *
 */
@Service
public class SendMailService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MailPropertiesService mailProperties;
	
	@Autowired 
	private TEmailLogDataService tEmailLogDataService;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private OLDateUtils olDateUtils;

	public int send(
			String sDest,
			String sSubject,
			String sContent) throws ClientProtocolException, IOException  {
		
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
				mailProperties.getServerId(), 
				mailProperties.getServerPassword());
        
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, creds);
		
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpPost post = new HttpPost(mailProperties.getMailServer());
        List<NameValuePair> postParms = new ArrayList<>();
        postParms.add(new BasicNameValuePair("from", mailProperties.getFrom()));
        postParms.add(new BasicNameValuePair("to", sDest));
        postParms.add(new BasicNameValuePair("h:Reply-To", mailProperties.getReplyTo()));
        postParms.add(new BasicNameValuePair("text", sContent));
        postParms.add(new BasicNameValuePair("subject", sSubject));
        post.setEntity(new UrlEncodedFormEntity(postParms));
        
		CloseableHttpResponse response = httpclient.execute(post);
		
		int status = response.getStatusLine().getStatusCode();
		HttpEntity respEntity = response.getEntity();
		
		String respContent = "";;
		if (respEntity.isStreaming()) {
			InputStream in = respEntity.getContent();
			
			byte[] b = new byte[1000];
			int n;
			while (( n = in.read(b)) != -1) {
				respContent += new String(Arrays.copyOfRange(b, 0, n));
			}
			
			in.close();
		}
		
		response.close();
		
		if (status != 200) {
			log.error("Failed to send email to " + sDest + " Subject: " + sSubject + ", sContent: " + sContent);
		}
		
		log.info("Response content" + respContent);
		
		TEmailLog tEmailLog = new TEmailLog(
				olDateUtils.getNow(),
				mailProperties.getMailServer(),
				mailProperties.getServerId(),
				mailProperties.getFrom(),
				sDest,
				mailProperties.getReplyTo(),
				sSubject,
				sContent,
				status,
				respContent);
		tEmailLogDataService.save(tEmailLog);
		
		return status;

	}
	
	public int send(
			String sDest,
			OLEmailMessageConstants messageConstant,
			String[] args,
			Locale locale) throws ClientProtocolException, IOException {
		
		EMailMessageBean bean = messageService.createEmailMesssageBean(messageConstant, args, locale);
		
		return send(sDest, bean.getSubject(), bean.getMessage());
	}

}


