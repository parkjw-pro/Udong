package com.ssafy.udong.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.udong.dao.ClubPostDao;
import com.ssafy.udong.dao.UserBoardDao;
import com.ssafy.udong.dto.CommentDto;
import com.ssafy.udong.dto.CommentResultDto;
import com.ssafy.udong.dto.ImageDto;
import com.ssafy.udong.dto.LikeDto;
import com.ssafy.udong.dto.ReportDto;
import com.ssafy.udong.dto.UserBoardDto;
import com.ssafy.udong.dto.UserBoardParamDto;
import com.ssafy.udong.dto.UserBoardResultDto;

@Service
public class UserBoardServiceImpl implements UserBoardService {

	private final Path root = Paths.get("uploads");
	
	@Autowired
	UserBoardDao dao;


	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	//글 작성
	@Override
	public int insertBoard(UserBoardDto boardDto, List<MultipartFile> files) {
		try {
			dao.insertBoard(boardDto);
			
			if( files != null && ! files.isEmpty() ) {
				File uploadDir = new File(root + File.separator + "userBoard" + File.separator +boardDto.getUserId());
				if (!uploadDir.exists()) uploadDir.mkdirs();
				
				List<String> fileNames = new ArrayList<>();
				Path path = root.resolve("userBoard");
				Path path2 = path.resolve(boardDto.getUserId());
				
				DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				Date nowDate = new Date();
				String tempDate = sdFormat.format(nowDate);
				
			    for(MultipartFile file : files) {
			    	int rank = 1;
			        try {
			        	
			        	
			        	System.out.println("file service");
			        	System.out.println(file.getOriginalFilename());
			        	Files.copy(file.getInputStream(), path2.resolve("udong_"+tempDate+file.getOriginalFilename()));
			          
			          
			          System.out.println("file service after"); 
			          
			        } catch (Exception e) {
			          throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			        }
			        
			        fileNames.add(file.getOriginalFilename());
			        System.out.println(file.getOriginalFilename());
			        
			        String fileName = "udong_"+tempDate+file.getOriginalFilename();
			        ImageDto imageDto = new ImageDto();
				    imageDto.setFileName(fileName);
				    imageDto.setFileSize(file.getSize());
				    imageDto.setFileContentType(file.getContentType());
				    
					String imageFileUrl = root + File.separator+ "userBoard" + File.separator+ boardDto.getUserId()+"/" + fileName;
					imageDto.setFileUrl(imageFileUrl);
				    
					dao.createUserBoardFile(imageDto);
					dao.connectionFile(boardDto);
					
					rank++;
			        
			    }
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	// 글전체조회
	@Override
	public  UserBoardResultDto listBoard(UserBoardParamDto userBoardParamDto) {

		UserBoardResultDto userBoardResultDto = new UserBoardResultDto();
		try {

			int count = dao.totalCountBoardList();            
			List<UserBoardDto> list = dao.listBoard(userBoardParamDto);

			userBoardResultDto.setList(list);
			userBoardResultDto.setCount(count);
			userBoardResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			userBoardResultDto.setResult(FAIL);
		}

		return userBoardResultDto;
	}
	// 글 검색어조회
	@Override
	public UserBoardResultDto searchWordListBoard(UserBoardParamDto userBoardParamDto) {

		UserBoardResultDto userBoardResultDto = new UserBoardResultDto();
		try {
			//게시물 총개수
			int count = dao.searchWordTotalCountBoardList(userBoardParamDto);
			List<UserBoardDto> list = dao.searchWordListBoard(userBoardParamDto);

			userBoardResultDto.setList(list);
			userBoardResultDto.setCount(count);
			userBoardResultDto.setResult(SUCCESS);

		}catch(Exception e) {
			e.printStackTrace();
			userBoardResultDto.setResult(FAIL);
		}

		return userBoardResultDto;
	}
	// 글상세 조회
	@Override
	public UserBoardResultDto detailBoard(String postId) {
		
		UserBoardDto userBoardDto = new UserBoardDto();
		UserBoardResultDto userBoardResultDto = new UserBoardResultDto();
		

		try {
			
			dao.boardReadCountUpdate(postId); // 조회수 1증가
			userBoardDto = dao.detailBoard(postId); //상세 리스트 가져오기 

			List<String> list = dao.SelectFileUrl(postId);
			
			userBoardResultDto.setDto(userBoardDto);
			userBoardResultDto.setFileUrl(list);
			
			return userBoardResultDto;

		}catch(Exception e) {
			e.printStackTrace();
			//return null;
		}
		return userBoardResultDto;
		
	}
	//글 수정
	@Override
	public int updateBoard(UserBoardDto userBoardDto){

		try {
			return  dao.updateBoard(userBoardDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//글 삭제
	@Override
	public int deleteBoard(String postId) {


		try {
			return dao.deleteBoard(postId);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int insertBoardDat(UserBoardDto userBoardDto) {
		try {
			return dao.insertBoardDat(userBoardDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createUserCom(CommentDto commentDto) {
		
		try {
			return dao.createUserCom(commentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public CommentResultDto selectUserCom(String postId) {
		
		CommentResultDto commentResultDto = new CommentResultDto();
		
		try {

			int count = dao.totalCountcomList();            
			List<CommentDto> list = dao.selectUserCom(postId);

			commentResultDto.setList(list);
			commentResultDto.setCount(count);
			commentResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			commentResultDto.setResult(FAIL);
		}

		return commentResultDto;

	}

	@Override
	public int createUserLike(LikeDto likeDto) {
		
		try {
			if(dao.selectUserLike(likeDto)==null) {
				dao.createUserLike(likeDto);
				return 1;
			}else {
				dao.deleteUserLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserCommLike(LikeDto likeDto) {
		try {
			if(dao.selectUserCommLike(likeDto)==null) {
				dao.createUserCommLike(likeDto);
				return 1;
			}else {
				dao.deleteUserCommLike(likeDto);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserReport(ReportDto reportDto) {
		try {
			dao.createUserReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createUserCommReport(ReportDto reportDto) {
		try {
			dao.createUserCommReport(reportDto);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	
}

