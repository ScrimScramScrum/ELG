//
//package springmvc.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import springmvc.domain.ResembleGame;
//import springmvc.domain.ResembleTask;
//import springmvc.repository.ResembleGameRepo;
//import springmvc.repository.ResembleTaskRepo;
//
//
//public class ResembleGameService {
//    
//    private ResembleGameRepo repo; 
//    
//    @Autowired
//    public void setResembleGameRepo(ResembleGameRepo repo){
//        System.out.println("Autowired ResembleGameRepo: ");
//        this.repo = repo; 
//    }
//    
//    public ResembleGame getResembleGame(int gameNumber){
//        return repo.getResembleGame(gameNumber);
//    }
//    
//}
