//package cz.mendelu.ea.utils.data;
//
//import cz.mendelu.ea.domain.band.Band;
//import cz.mendelu.ea.domain.band.BandService;
//import cz.mendelu.ea.domain.festival.Festival;
//import cz.mendelu.ea.domain.festival.FestivalService;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class Seeder {
////    private final FestivalService festivalService;
////    private final BandService bandService;
////
////    public Seeder(FestivalService festivalService, BandService bandService) {
////        this.festivalService = festivalService;
////        this.bandService = bandService;
////    }
////
////    private boolean shouldSeedData() {
////        List<Festival> festivals = festivalService.getAllFestivals();
////        for (Festival festival : festivals) {
////            if (festival.getBandsIds().isEmpty()) {
////                return true; // At least one festival exists without bands
////            }
////        }
////        return false; // All festivals have bands attached
//    }
//
//    @PostConstruct
//    public void seedDefaultData() {
//        if (!shouldSeedData()) {
//            log.info("--- Default data already seeded ---");
//            return;
//        }
//        log.info("--- Default data seeded ---");
//
////        List<Festival> festivals = festivalService.getAllFestivals();
////        List<Band> bands = bandService.getAllBands();
////
////        for (Festival festival : festivals) {
////            if (!bands.isEmpty()) {
////                Band band = bands.get(0);
////                festival.attachBand(band);
////                festivalService.updateFestival(festival.getId(), festival);
////                bands.remove(0);
////            } else {
////                log.warn("No more bands available to attach to festivals.");
////                break;
////            }
////        }
//
//    }
//}