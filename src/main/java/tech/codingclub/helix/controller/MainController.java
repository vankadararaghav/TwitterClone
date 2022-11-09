package tech.codingclub.helix.controller;

import org.apache.log4j.Logger;
import org.jooq.Condition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rishabh
 */
@Controller
@RequestMapping("/")
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/helloworld")
    public String getQuiz(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "hello";
    }
    @RequestMapping(method= RequestMethod.GET,value="/wikiapi")
    public String wikiresult(ModelMap modelmap,@RequestParam("keyword") String keyword,HttpServletRequest request,HttpServletResponse response)
    {
        WikiPediaFetcher wiki = new WikiPediaFetcher(keyword);
        WikiResult  w = wiki.getResult();
        modelmap.addAttribute("IMAGE",w.imageUrl);
        modelmap.addAttribute("DESCRIPTION",w.result);
        return "wikiresult";
    }
    @RequestMapping(method=RequestMethod.GET,value="/wiki")
    public String abcd(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
    {
          return "wiki";
    }




    @RequestMapping(method=RequestMethod.GET,value="/welcome")
    public String abcd1(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
    {

        Long user_id = ControllerUtils.getUserId(request);
        Condition condition = tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(user_id);
        List<Long> followers = new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID, tech.codingclub.helix.tables.Follower.FOLLOWER,Long.class,condition,null);

        followers.add(user_id);

        List<Tweet>  tweets = (List<Tweet>) GenericDB.getRows(tech.codingclub.helix.tables.Tweet.TWEET,Tweet.class,tech.codingclub.helix.tables.Tweet.TWEET.AUTHOR_ID.in(followers),null,tech.codingclub.helix.tables.Tweet.TWEET.ID.desc());
        List<TweetResponse> list = new ArrayList<TweetResponse>();
        for(int i=0;i<tweets.size();i++)
        {
            TweetResponse  tr = new TweetResponse();
            Member member  = (Member) new GenericDB<Member>().getRow(tech.codingclub.helix.tables.Member.MEMBER,Member.class,tech.codingclub.helix.tables.Member.MEMBER.ID.eq(tweets.get(i).author_id));
            tr.followerEmail = member.email;
            tr.tweet = tweets.get(i).message;
            tr.followerName = member.name;
            tr.tweet_id  = tweets.get(i).id;
            Long tweet_id = tweets.get(i).id;
            Condition condition1 = tech.codingclub.helix.tables.Retweet.RETWEET.TWEET_ID.eq(tweet_id);
            List<Long> tweet_count = new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Retweet.RETWEET.USER_ID,tech.codingclub.helix.tables.Retweet.RETWEET,Long.class,condition1,null);
            tr.count = tweet_count.size();
            list.add(tr);

        }
        modelmap.addAttribute("NAME",ControllerUtils.getCurrentMember(request).name);
        modelmap.addAttribute("TWEETS",list);

        return "welcome";
    }
    @RequestMapping(method=RequestMethod.GET,value="/alien")
    public String error(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
    {
        return "alien";
    }
   // @RequestMapping(method = RequestMethod.POST, value = "/signup")
   @RequestMapping(method=RequestMethod.GET,value="/signin")
   public String abcde(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
   {

       return "signin";
   }
    @RequestMapping(method=RequestMethod.GET,value="/signup")
    public String abcdefg(ModelMap modelmap,HttpServletRequest request,HttpServletResponse response)
    {
        return "signup";
    }
   @RequestMapping(method = RequestMethod.POST, value = "/signup")
   public   @ResponseBody  SignupResponse handleEncryp(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response) {

        boolean user_created;
        int x = GenericDB.getCount(tech.codingclub.helix.tables.Member.MEMBER,tech.codingclub.helix.tables.Member.MEMBER.EMAIL.eq(member.email));
        if(x==0)
        {
             user_created=true;
             member.role = "cm";
            new GenericDB<Member>().addRow(tech.codingclub.helix.tables.Member.MEMBER,member);
            SignupResponse sr = new SignupResponse("User Created",true);
            return sr;
        }
         SignupResponse sr = new SignupResponse("User Already Exists",false);
        return sr;
   }

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public
    @ResponseBody
    LoginResponse handleEncrypt(@RequestBody Member member , HttpServletRequest request, HttpServletResponse response) {
        Condition  condition =  tech.codingclub.helix.tables.Member.MEMBER.EMAIL.eq(member.email).and(tech.codingclub.helix.tables.Member.MEMBER.PASSWORD.eq(member.password));
        List<Member> x = (List<Member>) GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER,Member.class,condition,1);
        if(x!=null && x.size()>0)
        {
            Member membertemp = x.get(0);
            ControllerUtils.setUserSession(request,membertemp);
            return new LoginResponse(membertemp.id, true, "Logined Successfully");
        }
        else {

            return new  LoginResponse(null,false,"Wrong Combo");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/handle")
    public   @ResponseBody String handleEncry(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response) {
        return "Ok";
    }


    private class to {
    }
}