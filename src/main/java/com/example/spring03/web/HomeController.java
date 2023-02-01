package com.example.spring03.web;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.service.SoccerPostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
  
@Slf4j
@RequiredArgsConstructor
@Controller // 스프링 컨트롤러 컴포넌트 
public class HomeController {
    
    private final SoccerPostsService soccerPostService;
    
    @GetMapping("/") // 요청 URL/방식 매핑. 
    public String home(Model model) {
        log.info("home()");

        final String soccerUrl = "https://www.donga.com/ISSUE/2022WorldCup";
        Connection conn = Jsoup.connect(soccerUrl);
        
        
        try {
            Document document = conn.get();
            Elements fixutreElements = document.select("div.tab_con02 > img");
            
            for (int j = 0; j < fixutreElements.size(); j++) {
                final String url = fixutreElements.get(j).attr("abs:src");
                System.out.println(url);

                model.addAttribute("newsFixture", url);
            }
            
            Elements newsContent = document.select("span.txt > a");

            for (int j = 0; j < newsContent.size(); j++) {
                model.addAttribute("newsContent0", newsContent.get(0).text());
                model.addAttribute("newsContent1", newsContent.get(1).text());
            }
            
            final String soccerUrl2 = "https://www.donga.com/news/Issue/051011";
            Connection conn2 = Jsoup.connect(soccerUrl2);
            Document document2 = conn2.get();
            
            Elements titleElements = document2.select("span.tit");
            
            for (int j = 0; j < titleElements.size(); j++) {
                final String url = titleElements.get(j).text();
                
                
                model.addAttribute("newsTitle1", titleElements.get(1));
                model.addAttribute("newsTitle2", titleElements.get(2));
                model.addAttribute("newsTitle3", titleElements.get(3));
                model.addAttribute("newsTitle4", titleElements.get(4));
                
            }
            
          Elements imgElements = document2.select("div.articleList > div.thumb > a > img");
          
          for (int j = 0; j < imgElements.size(); j++) {
                model.addAttribute("newsImg1", imgElements.get(0).attr("abs:src"));
                model.addAttribute("newsImg2", imgElements.get(1).attr("abs:src"));
                model.addAttribute("newsImg3", imgElements.get(2).attr("abs:src"));
                model.addAttribute("newsImg4", imgElements.get(3).attr("abs:src"));
            }
          
          final String plTableUrl = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/%EC%88%9C%EC%9C%84/2kwbbcootiqqgmrzs6o5inle5";
          Connection conn3 = Jsoup.connect(plTableUrl);
          Document document3 = conn3.get();
          
          Elements plTableElements = document3.select("span.widget-match-standings__team--full-name");
          
          for (int j = 0; j < 10; j++) {

                model.addAttribute("plLeagueTable0", plTableElements.get(0).text());
                model.addAttribute("plLeagueTable1", plTableElements.get(1).text());
                model.addAttribute("plLeagueTable2", plTableElements.get(2).text());
                model.addAttribute("plLeagueTable3", plTableElements.get(3).text());
                model.addAttribute("plLeagueTable4", plTableElements.get(4).text());
                model.addAttribute("plLeagueTable5", plTableElements.get(5).text());
                model.addAttribute("plLeagueTable6", plTableElements.get(6).text());
                model.addAttribute("plLeagueTable7", plTableElements.get(7).text());
          }
          
          Elements gamesPlayed = document3.select("td.widget-match-standings__matches-played");
          
          for (int j = 0; j < gamesPlayed.size(); j++) {
                
                model.addAttribute("playedGames0", gamesPlayed.get(0).text());
                model.addAttribute("playedGames1", gamesPlayed.get(1).text());
                model.addAttribute("playedGames2", gamesPlayed.get(2).text());
                model.addAttribute("playedGames3", gamesPlayed.get(3).text());
                model.addAttribute("playedGames4", gamesPlayed.get(4).text());
                model.addAttribute("playedGames5", gamesPlayed.get(5).text());
                model.addAttribute("playedGames6", gamesPlayed.get(6).text());
                model.addAttribute("playedGames7", gamesPlayed.get(7).text());
          }
          
          Elements gamesWon = document3.select("td.widget-match-standings__matches-won");
          
          for (int j = 0; j < gamesWon.size(); j++) {
                
                model.addAttribute("wonGames0", gamesWon.get(0).text());
                model.addAttribute("wonGames1", gamesWon.get(1).text());
                model.addAttribute("wonGames2", gamesWon.get(2).text());
                model.addAttribute("wonGames3", gamesWon.get(3).text());
                model.addAttribute("wonGames4", gamesWon.get(4).text());
                model.addAttribute("wonGames5", gamesWon.get(5).text());
                model.addAttribute("wonGames6", gamesWon.get(6).text());
                model.addAttribute("wonGames7", gamesWon.get(7).text());
          }
          
          Elements gamesDrawn = document3.select("td.widget-match-standings__matches-drawn");
          
          for (int j = 0; j < gamesDrawn.size(); j++) {
                
                model.addAttribute("drawGames0", gamesDrawn.get(0).text());
                model.addAttribute("drawGames1", gamesDrawn.get(1).text());
                model.addAttribute("drawGames2", gamesDrawn.get(2).text());
                model.addAttribute("drawGames3", gamesDrawn.get(3).text());
                model.addAttribute("drawGames4", gamesDrawn.get(4).text());
                model.addAttribute("drawGames5", gamesDrawn.get(5).text());
                model.addAttribute("drawGames6", gamesDrawn.get(6).text());
                model.addAttribute("drawGames7", gamesDrawn.get(7).text());
          }
          
          Elements gamesLost = document3.select("td.widget-match-standings__matches-lost");
          
          for (int j = 0; j < gamesLost.size(); j++) {
                
                model.addAttribute("gamesLost0", gamesLost.get(0).text());
                model.addAttribute("gamesLost1", gamesLost.get(1).text());
                model.addAttribute("gamesLost2", gamesLost.get(2).text());
                model.addAttribute("gamesLost3", gamesLost.get(3).text());
                model.addAttribute("gamesLost4", gamesLost.get(4).text());
                model.addAttribute("gamesLost5", gamesLost.get(5).text());
                model.addAttribute("gamesLost6", gamesLost.get(6).text());
                model.addAttribute("gamesLost7", gamesLost.get(7).text());
          }
          
          Elements gamePoints = document3.select("td.widget-match-standings__pts");
          
          for (int j = 0; j < gamePoints.size(); j++) {
                
                model.addAttribute("gamePoints0", gamePoints.get(0).text());
                model.addAttribute("gamePoints1", gamePoints.get(1).text());
                model.addAttribute("gamePoints2", gamePoints.get(2).text());
                model.addAttribute("gamePoints3", gamePoints.get(3).text());
                model.addAttribute("gamePoints4", gamePoints.get(4).text());
                model.addAttribute("gamePoints5", gamePoints.get(5).text());
                model.addAttribute("gamePoints6", gamesLost.get(6).text());
                model.addAttribute("gamePoints7", gamesLost.get(7).text());
          }
          
          final String plFixture = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/%EC%9D%BC%EC%A0%95-%EA%B2%B0%EA%B3%BC/2kwbbcootiqqgmrzs6o5inle5";
          Connection conn4 = Jsoup.connect(plFixture);

          
          Document document4 = conn4.get();
               
          Elements plLiveFixture = document4.select("span.team-name");
              
          for (int j = 0; j < plLiveFixture.size(); j++) { 
              model.addAttribute("plLeagueFixture0", plLiveFixture.get(0).text());
              model.addAttribute("plLeagueFixture1", plLiveFixture.get(1).text());
//              model.addAttribute("plLeagueFixture2", plLiveFixture.get(2).text());
//              model.addAttribute("plLeagueFixture3", plLiveFixture.get(3).text());
//              model.addAttribute("plLeagueFixture4", plLiveFixture.get(4).text());
//              model.addAttribute("plLeagueFixture5", plLiveFixture.get(5).text());
//              model.addAttribute("plLeagueFixture6", plLiveFixture.get(6).text());
//              model.addAttribute("plLeagueFixture7", plLiveFixture.get(7).text());
//              model.addAttribute("plLeagueFixture8", plLiveFixture.get(8).text());
//              model.addAttribute("plLeagueFixture9", plLiveFixture.get(9).text());
//              model.addAttribute("plLeagueFixture10", plLiveFixture.get(10).text());
//              model.addAttribute("plLeagueFixture11", plLiveFixture.get(11).text());
//              model.addAttribute("plLeagueFixture12", plLiveFixture.get(12).text());
//              model.addAttribute("plLeagueFixture13", plLiveFixture.get(13).text());
              
          }
          
          Elements matchTime = document4.select("div.match-status > time");

          for (int j = 0; j < matchTime.size(); j++) {
              model.addAttribute("matchTime0", matchTime.get(0).text());
//              model.addAttribute("matchTime1", matchTime.get(1).text());
//              model.addAttribute("matchTime2", matchTime.get(2).text());
//              model.addAttribute("matchTime3", matchTime.get(3).text());
//              model.addAttribute("matchTime4", matchTime.get(4).text());
//              model.addAttribute("matchTime5", matchTime.get(5).text());
//              model.addAttribute("matchTime6", matchTime.get(6).text());
              
              
          }
          
          Elements fixtureDate = document4.select("div.nav-switch__label");

          for (int j = 0; j < fixtureDate.size(); j++) {
              final String fixDate = fixtureDate.get(j).text();

              model.addAttribute("plDate0", fixtureDate.get(0));
          } 
          
          // 여기는 득점 선수 이름 부분
          final String topScoreUrl = "https://www.bbc.com/sport/football/premier-league/top-scorers";
          Connection conn5 = Jsoup.connect(topScoreUrl);

          
          Document document5 = conn5.get();
               
          Elements topScore = document5.select("span.gs-u-vh");
              
          for (int j = 0; j < topScore.size(); j++) { 
              model.addAttribute("scoreName0", topScore.get(7));
              model.addAttribute("scoreName1", topScore.get(9));
              model.addAttribute("scoreName2", topScore.get(11));
              model.addAttribute("scoreName3", topScore.get(13));
              model.addAttribute("scoreName4", topScore.get(15));
          }
          // 득점 기록 골 수
          final String scoreNumberUrl = "https://www.topscorersfootball.com/premier-league";
          Connection conn6 = Jsoup.connect(scoreNumberUrl);

          
          Document document6 = conn6.get();
               
          Elements topScoreNumber = document6.select("strong");
              
          for (int j = 0; j < topScore.size(); j++) { 
              model.addAttribute("scoreRecord0", topScoreNumber.get(2));
              model.addAttribute("scoreRecord1", topScoreNumber.get(3));
              model.addAttribute("scoreRecord2", topScoreNumber.get(4));
              model.addAttribute("scoreRecord3", topScoreNumber.get(5));
              model.addAttribute("scoreRecord4", topScoreNumber.get(6));
          }
          // 득점 골 수 끝
          
          
          
          // 뉴스 이적설 소식
          final String transferURL = "https://www.goal.com/kr/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC/%EC%9D%B4%EC%A0%81/1/k94w8e1yy9ch14mllpf4srnks";
          Connection conn7 = Jsoup.connect(transferURL);
          Document document7 = conn7.get();
          
          Elements transferRumor = document7.select("span.headline_image__AaEuQ > img");
          for (int j = 0; j < transferRumor.size(); j++) {
              final String url = transferRumor.get(j).attr("abs:src");
              model.addAttribute("transferImage0", transferRumor.get(0).attr("abs:src"));
              model.addAttribute("transferImage1", transferRumor.get(1).attr("abs:src"));
              model.addAttribute("transferImage2", transferRumor.get(2).attr("abs:src"));
              model.addAttribute("transferImage3", transferRumor.get(3).attr("abs:src"));
            }
          Elements transferTitle = document7.select("h3.title > span");
          
          for (int j = 0; j < transferTitle.size(); j++) {
                model.addAttribute("transferTitle0", transferTitle.get(0).text());
                model.addAttribute("transferTitle1", transferTitle.get(1).text());
                model.addAttribute("transferTitle2", transferTitle.get(2).text());
                model.addAttribute("transferTitle3", transferTitle.get(3).text());
          }
          
          Elements transferLink = document7.select("div.poster-wrapper > a");
          
          for (int j = 0; j < transferLink.size(); j++) {
                model.addAttribute("transferLink1", transferLink.get(0).attr("abs:href"));
          }
          
          // 프리미어리그 뉴스 소식
//          final String transferURL = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/2kwbbcootiqqgmrzs6o5inle5s";
//          Connection conn7 = Jsoup.connect(transferURL);
//          Document document7 = conn7.get();
//          
//          Elements transferRumor = document7.select("span.headline_image__AaEuQ > img");
//          for (int j = 0; j < transferRumor.size(); j++) {
//              final String url = transferRumor.get(j).attr("abs:src");
//              model.addAttribute("transferImage0", transferRumor.get(0).attr("abs:src"));
//              model.addAttribute("transferImage1", transferRumor.get(1).attr("abs:src"));
//              model.addAttribute("transferImage2", transferRumor.get(2).attr("abs:src"));
//              model.addAttribute("transferImage3", transferRumor.get(3).attr("abs:src"));
//            }
//          Elements transferTitle = document7.select("h3.title > span");
//          
//          for (int j = 0; j < transferTitle.size(); j++) {
//                model.addAttribute("transferTitle0", transferTitle.get(0).text());
//                model.addAttribute("transferTitle1", transferTitle.get(1).text());
//                model.addAttribute("transferTitle2", transferTitle.get(2).text());
//                model.addAttribute("transferTitle3", transferTitle.get(3).text());
//          }
          
          List<SoccerPosts> list = soccerPostService.read();
          model.addAttribute("list", list);
          
          
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
    
    
  
  }