package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

public class UserAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	private File headImg; //��ʱ�ļ�
	private String headImgFileName;
	private String headImgContentType;// �ύ�������ļ���MIMI����
	private File userExcel;
	private String userExcelFileName;
	private String userExcelContentType;
	//�б�ҳ��
	public String listUI(){
		userList = userService.findObjects();
		return "listUI";
	}
	//��ת������ҳ��
	public String addUI(){
		return "addUI";
	}
	//��������
	public String add(){
		if(user != null){
			if(headImg != null){
					
			
				try {
					String realPath = ServletActionContext.getServletContext().getRealPath("/upload/user");
					String fileName =  UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(realPath,fileName));
					user.setHeadImg("user/"+fileName);
				} catch(FileNotFoundException e){
					
					e.printStackTrace();
				}catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
			userService.save(user);
		}
		return "list";
	}
	//��ת���༭ҳ��
		public String editUI(){
			if (user != null && user.getId() != null) {
				System.out.println(user.getHeadImg());
				user = userService.findObjectbyId(user.getId());
			}
			return "editUI";
		}
		//����༭
		public String edit(){
			if(user != null){
				if(headImg != null){
						
				
					try {
						String realPath = ServletActionContext.getServletContext().getRealPath("/upload/user");
						String fileName =  UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
						FileUtils.copyFile(headImg, new File(realPath,fileName));
						user.setHeadImg("user/"+fileName);
					} catch(FileNotFoundException e){
						
						e.printStackTrace();
					}catch (IOException e) {
						
						e.printStackTrace();
					}
				
				}
				userService.update(user);
			}
			return "list";
		}
	//ɾ��
	public String delete(){
		if(user != null && user.getId() != null){

			userService.delete(user.getId());
		}
		return "list";
	}
	//����ɾ��
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	//execl���� ����
	public void exportExcel(){
		try {
			//1�������û��б�
			userList = userService.findObjects();
			//2������
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("�û��б�.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userList, outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String  importExcel(){

		//1����ȡexcel�ļ�
				if(userExcel != null){
					//�Ƿ���excel
					if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
						//2������
						userService.importExcel(userExcel, userExcelFileName);
					}
				}
				return "list";

	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	

	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	
	
	
	
}
