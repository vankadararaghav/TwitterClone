package tech.codingclub.helix.controller;
import org.jooq.Condition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping(method= RequestMethod.GET,value = "/create-post")
    public   String createtweet1(ModelMap  modelmap , HttpServletRequest request, HttpServletResponse response)
    {
        modelmap.addAttribute("NAME",ControllerUtils.getCurrentMember(request).name);
        Long user_id = ControllerUtils.getUserId(request);
        Condition condition = tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(user_id);
        List<Long> followers = new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID, tech.codingclub.helix.tables.Follower.FOLLOWER,Long.class,condition,null);
        followers.add(user_id);

        List<Tweet>  tweets = (List<Tweet>) GenericDB.getRows(tech.codingclub.helix.tables.Tweet.TWEET,Tweet.class,tech.codingclub.helix.tables.Tweet.TWEET.AUTHOR_ID.in(followers),null);
        List<TweetResponse> list = new ArrayList<TweetResponse>();
        for(int i=0;i<tweets.size();i++)
        {
            TweetResponse  tr = new TweetResponse();
            Member member  = (Member) new GenericDB<Member>().getRow(tech.codingclub.helix.tables.Member.MEMBER,Member.class,tech.codingclub.helix.tables.Member.MEMBER.ID.eq(tweets.get(i).author_id));
            tr.followerEmail = member.email;
            tr.tweet = tweets.get(i).message;
            tr.followerName = member.name;
            list.add(tr);
        }
        modelmap.addAttribute("NAME",ControllerUtils.getCurrentMember(request).name);
        modelmap.addAttribute("TWEETS",list);
        return "welcome";
    }




    @RequestMapping(method= RequestMethod.POST,value = "/create-post")
    public @ResponseBody  String createtweet(@RequestBody String message , HttpServletRequest request, HttpServletResponse response)
    {
        Tweet tweet = new Tweet(message,null,  new Date().getTime(),ControllerUtils.getUserId(request));
        new GenericDB<Tweet>().addRow(tech.codingclub.helix.tables.Tweet.TWEET,tweet);
        return "posted successfully";
    }



    @RequestMapping(method= RequestMethod.GET,value = "/recommendations")
    public   String recommendations(ModelMap modelMap , HttpServletRequest request, HttpServletResponse response)
    {
        Member member = ControllerUtils.getCurrentMember(request);
        List<Long> followerIds =  new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID,tech.codingclub.helix.tables.Follower.FOLLOWER,Long.class,tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(member.id),null);
         Condition condition = tech.codingclub.helix.tables.Member.MEMBER.ID.notEqual(member.id).and(tech.codingclub.helix.tables.Member.MEMBER.ID.notIn(followerIds));
         List<Member> members = (List<Member>) GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER,Member.class,condition,10);
        for(Member memb :members)
        {
            if(followerIds.contains(memb.id))
                memb.is_followed=true;
        }
        modelMap.addAttribute("RECOMMENDATIONS",members);
        modelMap.addAttribute("NAME",ControllerUtils.getCurrentMember(request).name);
        return "recommendations";
    }




    @RequestMapping(method= RequestMethod.POST,value = "/follow-user")
    public @ResponseBody  String follow(@RequestBody Long id, HttpServletRequest request, HttpServletResponse response)
    {
        Long memberId = ControllerUtils.getUserId(request);
        Follower f = new Follower(memberId,id);
        if(memberId!=null && id!=null && memberId!=id)
        new GenericDB<Follower>().addRow(tech.codingclub.helix.tables.Follower.FOLLOWER,f);
        return "Connected Successfully";
    }


    @RequestMapping(method= RequestMethod.POST,value = "/retweet")
    public @ResponseBody  String retweet(@RequestBody Long id, HttpServletRequest request, HttpServletResponse response)
    {
        Long memberId = ControllerUtils.getUserId(request);
        Retweet retweet = new Retweet(id,memberId);
        new GenericDB<Retweet>().addRow(tech.codingclub.helix.tables.Retweet.RETWEET,retweet);
        return "retweet added";
    }





    @RequestMapping(method= RequestMethod.POST,value = "/unfollow-user")
    public @ResponseBody  String unfollow(@RequestBody Long id, HttpServletRequest request, HttpServletResponse response)
    {
        Long memberId = ControllerUtils.getUserId(request);
        Follower follower = new Follower(memberId,id);
        Condition condition = tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID.eq(id).and(tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(memberId));
        new GenericDB<Follower>().deleteRow(tech.codingclub.helix.tables.Follower.FOLLOWER,condition);
        return "Unfollowed Successfully";
    }






    @RequestMapping(method= RequestMethod.GET,value = "/followed")
    public   String followed(ModelMap modelMap , HttpServletRequest request, HttpServletResponse response)
    {
         Long UserId = ControllerUtils.getUserId(request);

         List<Long> x = new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID,tech.codingclub.helix.tables.Follower.FOLLOWER,Long.class,tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(UserId),10);
         List<?> members =  GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER,Member.class,tech.codingclub.helix.tables.Member.MEMBER.ID.in(x),40);
         modelMap.addAttribute("FOLLOWED",members);
         return "followed";
    }

   @RequestMapping(method=RequestMethod.GET,value="/SignOut")
   public String  SignOut(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
   {
       return  "signin";
   }


}
