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
	private File headImg; //临时文件
	private String headImgFileName;
	private String headImgContentType;// 提交过来的文件的MIMI类型
	private File userExcel;
	private String userExcelFileName;
	private String userExcelContentType;
	//列表页面
	public String listUI(){
		userList = userService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		return "addUI";
	}
	//保存新增
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
	//跳转到编辑页面
		public String editUI(){
			if (user != null && user.getId() != null) {
				System.out.println(user.getHeadImg());
				user = userService.findObjectbyId(user.getId());
			}
			return "editUI";
		}
		//保存编辑
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
	//删除
	public String delete(){
		if(user != null && user.getId() != null){

			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	//execl导出 下载
	public void exportExcel(){
		try {
			//1、查找用户列表
			userList = userService.findObjects();
			//2、导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
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

		//1、获取excel文件
				if(userExcel != null){
					//是否是excel
					if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
						//2、导入
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
