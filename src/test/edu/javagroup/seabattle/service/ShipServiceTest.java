//package edu.javagroup.seabattle.service;
//
//import edu.javagroup.seabattle.common.utils.CommonPanelService;
//import edu.javagroup.seabattle.service.impl.ShipServiceImpl;
//import edu.javagroup.seabattle.singleton.MinePanelSingleton;
//import org.assertj.core.api.AssertionsForClassTypes;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * перед использованием раскомментировать классы
// * <p>
// * test -> edu.javagroup.seabattle.common.utils.CommonPanelService
// * test -> edu.javagroup.seabattle.common.utils.impl.CommonPanelServiceImpl
// *
// * @author kaa
// * @version 1.4
// */
//@SpringBootTest
//public class ShipServiceTest {
//
//    @Autowired
//    private ShipService shipService;
//
//    @Autowired
//    private CommonPanelService commonPanelService;
//
//    private static final String COORDINATE_LIST_FIELD = "coordinateList";
//
//    //------------------------------------------------------------------------------------------------------------------
//
//    @Test
//    void oopTest() {
//        // проверки использования парадигм
//        assertThat(ShipServiceImpl.class.getInterfaces()[0].getName().equals(ShipService.class.getName())).isTrue();
//    }
//
//    @Test
//    void fieldTest() {
//        // проверка, есть ли необходимые поля
//        Field[] fieldArray = ShipServiceImpl.class.getDeclaredFields();
//        List<String> fieldNameArray = Arrays.stream(fieldArray).map(Field::getName).collect(Collectors.toCollection(() -> new ArrayList<>(1)));
//        AssertionsForClassTypes.assertThat(fieldNameArray.contains(COORDINATE_LIST_FIELD)).isTrue();
//        // проверка инкапсуляции поля
//        try {
//            Field field = ShipServiceImpl.class.getDeclaredField(COORDINATE_LIST_FIELD);
//            field.setAccessible(true);
//            AssertionsForClassTypes.assertThat(field.getModifiers() == Modifier.PRIVATE).isTrue();
//        } catch (NoSuchFieldException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    void checkShipCountTest1() {
//        // проверка на 19ти палубах по всей панели
//        MinePanelSingleton.instance(commonPanelService.getPanel19());
//        assertThat(shipService.checkShipCount()).isFalse();
//        // проверка на 20ти палубах по всей панели
//        MinePanelSingleton.instance(commonPanelService.getPanel20());
//        assertThat(shipService.checkShipCount()).isTrue();
//    }
//
//    @Test
//    void checkShipCountTest2() {
//        MinePanelSingleton.instance(commonPanelService.getPanel20());
//        // 1 палуба - 4шт
//        assertThat(shipService.checkShipCount(1) == 4).isTrue();
//        // 2 палубы - 3шт
//        assertThat(shipService.checkShipCount(2) == 3).isTrue();
//        // 3 палубы - 2шт
//        assertThat(shipService.checkShipCount(3) == 2).isTrue();
//        // 4 палубы - 1шт
//        assertThat(shipService.checkShipCount(4) == 1).isTrue();
//    }
//
//    /**
//     * запускать отдельно
//     * <p>
//     * перед запуском этого теста, сменить модификатор метода getHorizontalCoordinateList в ShipServiceImpl,
//     * после положительного результата проверки, вернуть прежний модификатор
//     */
//    @Test
//    @Disabled
//    void getHorizontalCoordinateListTest() {
//        assertThat(new ShipServiceImpl().getHorizontalCoordinateList(commonPanelService.getPanel20()).size() == 110).isTrue();
//    }
//
//    /**
//     * запускать отдельно
//     * <p>
//     * перед запуском этого теста, сменить модификатор метода getVerticalCoordinateList в ShipServiceImpl,
//     * после положительного результата проверки, вернуть прежний модификатор
//     */
//    @Test
//    @Disabled
//    void getVerticalCoordinateListTest() {
//        assertThat(new ShipServiceImpl().getVerticalCoordinateList(commonPanelService.getPanel20()).size() == 110).isTrue();
//    }
//
//    /**
//     * запускать отдельно
//     * <p>
//     * перед запуском этого теста, сменить модификатор метода findShipDeck в ShipServiceImpl,
//     * после положительного результата проверки, вернуть прежний модификатор
//     */
//    @Test
//    @Disabled
//    void findShipDeckTest() {
//        MinePanelSingleton.instance(commonPanelService.getPanel20());
//        ShipServiceImpl shipServiceImpl = new ShipServiceImpl();
//        shipServiceImpl.checkShipCount();
//        assertThat(shipServiceImpl.findShipDeck(4) == 1).isTrue();
//        assertThat(shipServiceImpl.findShipDeck(3) == 2).isTrue();
//        assertThat(shipServiceImpl.findShipDeck(2) == 3).isTrue();
//        assertThat(shipServiceImpl.findShipDeck(1) == 4).isTrue();
//    }
//}