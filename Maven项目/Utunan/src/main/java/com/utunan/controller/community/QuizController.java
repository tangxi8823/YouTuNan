package com.utunan.controller.community;

import com.utunan.pojo.community.Quiz;
import com.utunan.pojo.community.Tag;
import com.utunan.pojo.user.User;
import com.utunan.pojo.util.BigQuiz;
import com.utunan.pojo.util.Page;
import com.utunan.service.community.QuizService;
import com.utunan.service.community.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 孙程程
 * @description: 问答首页
 * @date 2018/11/19 16:24
 */
@Controller
public class QuizController {

	@Autowired
	private QuizService quizService;
	@Autowired
	private TagService tagService;

	/**
	 * @author  孙程程
	 * @description 根据发表时间展示问答列表
	 * @date  17:52 2018/11/20
	 * @param  request
	 * @return  java.lang.String
	 */
	@RequestMapping(value="/quiz1")
	public String displayQuizByTime(HttpServletRequest request){
		String url="quiz1";
		String pageNum=request.getParameter("pageNum");
		//判断当前页
		int num=0;
		if(pageNum==null || pageNum.equals("")){
			num=1;
		}else{
			num=Integer.parseInt(pageNum);
		}
		//提问列表
		List<Quiz> quizList=this.quizService.getQuizByTime(num,6);
		//提问数量
		Long quizNumber = this.quizService.countAllQuiz();
		//提取提问列表的quizId
		List<Long> quizIdList=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			quizIdList.add(quizList.get(i).getQuizId());
		}
		//根据quizID获得用户表
		List<User> userList=new ArrayList<>();
		//根据quizID获得commentNumber列表
		List<Long> commentNumber=new ArrayList<>();
		//根据quizID获得tagList列表
		List<List<Tag>> quizTagList=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			userList.add(this.quizService.findUserByQuizId(quizIdList.get(i)));
			commentNumber.add(this.quizService.countCommentByQuizId(quizIdList.get(i)));
			quizTagList.add(this.quizService.selectTagByQuizId(quizIdList.get(i)));
		}
		//将Quiz,User,commentNumber,tagList封装
		List<BigQuiz> objects=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			BigQuiz bigQuiz=new BigQuiz();
			bigQuiz.setQuiz(quizList.get(i));
			bigQuiz.setUser(userList.get(i));
			bigQuiz.setCommentNumber(commentNumber.get(i));
			bigQuiz.setTagList(quizTagList.get(i));
			objects.add(bigQuiz);
		}
		//热门标签
		List<Tag> hotTagList=this.tagService.getTop10Tag();
		//热门标签的回答数量
		List<Long> hotTagNumber=this.tagService.getTop10TagNumber();
		//封装热门标签和标签数量
		Object[][] tag=new Object[10][2];
		for(int i=0; i<hotTagList.size(); i++){
			tag[i][0]=hotTagList.get(i);
			tag[i][1]=hotTagNumber.get(i);
		}
		//封装分页
		Page<BigQuiz> p = new Page<>(num, 6);
		p.setList(objects);
		p.setTotalCount(quizNumber);
		//返回数据
		request.setAttribute("page",p);
		request.setAttribute("url",url);
		request.setAttribute("tag",tag);
		request.setAttribute("objects",objects);
		return "community/quiz";
	}

	/**
	 * @author  孙程程
	 * @description 根据点赞数量展示问答列表
	 * @date  17:52 2018/11/20
	 * @param  request
	 * @return  java.lang.String
	 */
	@RequestMapping(value="/quiz2")
	public String displayQuizByPraise(HttpServletRequest request){
		String url="quiz2";
		String pageNum=request.getParameter("pageNum");
		//判断当前页
		int num=0;
		if(pageNum==null || pageNum.equals("")){
			num=1;
		}else{
			num=Integer.parseInt(pageNum);
		}
		//问答列表
		List<Quiz> quizList=this.quizService.getQuizByPraise(num,6);
		//问题数量
		Long quizNumber = this.quizService.countAllQuiz();
		//提取提问列表的quizId
		List<Long> quizIdList=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			quizIdList.add(quizList.get(i).getQuizId());
		}
		//根据quizID获得用户表
		List<User> userList=new ArrayList<>();
		//根据quizID获得commentNumber列表
		List<Long> commentNumber=new ArrayList<>();
		//根据quizID获得tagList列表
		List<List<Tag>> quizTagList=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			userList.add(this.quizService.findUserByQuizId(quizIdList.get(i)));
			commentNumber.add(this.quizService.countCommentByQuizId(quizIdList.get(i)));
			quizTagList.add(this.quizService.selectTagByQuizId(quizIdList.get(i)));
		}
		//将Quiz,User,commentNumber,tagList封装
		List<BigQuiz> objects=new ArrayList<>();
		for (int i=0;i<quizList.size(); i++){
			BigQuiz bigQuiz=new BigQuiz();
			bigQuiz.setQuiz(quizList.get(i));
			bigQuiz.setUser(userList.get(i));
			bigQuiz.setCommentNumber(commentNumber.get(i));
			bigQuiz.setTagList(quizTagList.get(i));
			objects.add(bigQuiz);
		}
		//热门标签
		List<Tag> hotTagList=this.tagService.getTop10Tag();
		//热门标签的回答数量
		List<Long> hotTagNumber=this.tagService.getTop10TagNumber();
		//封装热门标签和标签数量
		Object[][] tag=new Object[10][2];
		for(int i=0; i<hotTagList.size(); i++){
			tag[i][0]=hotTagList.get(i);
			tag[i][1]=hotTagNumber.get(i);
		}
		//封装分页
		Page<BigQuiz> p = new Page<>(num, 6);
		p.setList(objects);
		p.setTotalCount(quizNumber);
		//返回数据
		request.setAttribute("page",p);
		request.setAttribute("url",url);
		request.setAttribute("tag",tag);
		request.setAttribute("objects",objects);
		return "community/quiz";
	}
}
