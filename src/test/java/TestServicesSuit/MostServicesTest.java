package TestServicesSuit;


import com.revature.frittte.chat.Chat;
import com.revature.frittte.chat.ChatDao;
import com.revature.frittte.chat.ChatService;
import com.revature.frittte.creditcard.CreditCard;
import com.revature.frittte.creditcard.CreditCardDao;
import com.revature.frittte.creditcard.CreditCardService;
import com.revature.frittte.customer.Customer;
import com.revature.frittte.customer.CustomerDao;
import com.revature.frittte.customer.CustomerService;
import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.exception.InvalidRequestException;
import com.revature.frittte.food.Food;
import com.revature.frittte.food.FoodDao;
import com.revature.frittte.food.FoodService;
import com.revature.frittte.message.Message;
import com.revature.frittte.message.MessageDao;
import com.revature.frittte.message.MessageService;
import com.revature.frittte.order.OrderDao;
import com.revature.frittte.order.OrderData;
import com.revature.frittte.order.OrderServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MostServicesTest {

    Customer stu;
    Food stu2;
    Chat stu3;
    CreditCardService sut;
    CustomerService sut2;

    FoodService sut3;

    OrderServices sut4;
    MessageService sut5;

    ChatService sut6;

    FoodDao mockFoodDao;

    CreditCardDao mockCreditCardDao;
    CustomerDao mockCustomerDao;
    OrderDao mockOrderDao;

    MessageDao mockMessageDao;
    ChatDao mockChatDao;

    @BeforeEach
    public void testPrep(){
        mockCreditCardDao = mock(CreditCardDao.class);
        mockCustomerDao = mock(CustomerDao.class);
        mockFoodDao = mock(FoodDao.class);
        mockOrderDao = mock(OrderDao.class);
        mockMessageDao = mock(MessageDao.class);
        mockChatDao =mock(ChatDao.class);
        stu = new Customer("Geoff", "1234", "Geoffy", "Blois", "10-9-1900", false,false);
        stu2 = new Food("1","water", 12, 10, 12, false);
        stu3 = new Chat("1",stu, "Mission Secrets","6-11-2022", "Bob");
        sut = new CreditCardService(mockCreditCardDao);
        sut2 = new CustomerService(mockCustomerDao);
        sut3 = new FoodService(mockFoodDao);
        sut4 = new OrderServices(mockOrderDao);
        sut5 = new MessageService(mockMessageDao);
        sut6 = new ChatService(mockChatDao);

    }


    // Credit Card Input
    @Test
    public void test_validInput_givenCreditCard_returnTrue(){

        CreditCard creditCard = new CreditCard(5555555,"Geoffrey", 999,"9-23", 20, stu );

        boolean actualResult = sut.validateInput(creditCard);

        Assertions.assertTrue(actualResult);
    }

    // Customer
    @Test
    public void test_validInput_givenCustomer_returnTrue(){

        Customer customer = new Customer("GeoffreyB", "password", "Geoff", "Blois", "12-9-2021", true, false);

        boolean actualResult = sut2.validateInput(customer);

        Assertions.assertTrue(actualResult);
    }


    @Test
    public void test_ValidInput_createCustomer_ReturnCustomer(){
        Customer customer = new Customer("Movie", "pass", "Move","ie","12-9-2052", false, false);

        when(mockCustomerDao.save(customer)).thenReturn(customer);

        Customer actualCustomer = sut2.create(customer);

        Assertions.assertEquals("Movie", actualCustomer.getUsername());
        Assertions.assertEquals("pass", actualCustomer.getPassword());
        Assertions.assertEquals("Move", actualCustomer.getFname());
        Assertions.assertEquals("ie", actualCustomer.getLname());
        Assertions.assertEquals("12-9-2052", actualCustomer.getDob());
        Assertions.assertEquals(false, actualCustomer.isEmployee());
        Assertions.assertEquals(false, actualCustomer.isAdmin());

        verify(mockCustomerDao, times(1)).save(customer);
    }

    @Test
    public void test_create_givenRepeatedUserInformation_throwsInvalidRequestException(){
        Customer customer = new Customer("Movie", "", "Move","ie","12-9-2052" ,false , false);
        when(mockCustomerDao.existsById(customer.getUsername())).thenReturn(true);

        Assertions.assertThrows(InvalidRequestException.class, () -> { sut2.create(customer);});
        verify(mockCustomerDao, times(0)).save(customer);
    }

    //Food
    @Test
    public void test_validInput_givenItem_returnTrue(){

        Food food = new Food("1","water", 12, 10, 12, false );

        boolean actualResult = sut3.validateInput(food);

        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_ValidInput_createFood_ReturnFood(){
        Food food = new Food("1","water", 12, 10, 12, false);

        when(mockFoodDao.save(food)).thenReturn(food);

        Food actualCustomer = sut3.create(food);

        Assertions.assertEquals("1", actualCustomer.getId());
        Assertions.assertEquals("water", actualCustomer.getItemName());
        Assertions.assertEquals(12, actualCustomer.getCost());
        Assertions.assertEquals(10, actualCustomer.getWeight());
        Assertions.assertEquals(12, actualCustomer.getVolume());
        Assertions.assertEquals(false, actualCustomer.isFrozen());

        verify(mockFoodDao, times(1)).save(food);
    }

    @Test
    public void test_create_givenRepeatedFoodInformation_throwsInvalidRequestException(){
        Food food = new Food("1","water", -1, 10, 12, false);
        when(mockFoodDao.existsById(food.getItemName())).thenReturn(true);

        Assertions.assertThrows(InvalidRequestException.class, () -> { sut3.create(food);});
        verify(mockFoodDao, times(0)).save(food);
    }

    //order
    @Test
    public void test_validInput_givenOrder_returnTrue(){

        OrderData orderData = new OrderData(1,"10-12-1956", stu2, stu, "This water is expensive" );

        boolean actualResult = sut4.validateInput(orderData);

        Assertions.assertTrue(actualResult);
    }

    // Message

    @Test
    public void test_validInput_givenMessage_returnFalse(){

        Message message = new Message(1, stu ,"", "6-11-2022");


        boolean actualResult = sut5.validateInput(message);

        Assertions.assertFalse(actualResult);
    }

//    @Test
//    public void test_create_givenRepeatedMessageInformation_throwsInvalidRequestException(){
//        Message message = new Message(1, stu ,"Hello this is a message", "");
//        when(mockMessageDao.existsById(message.getId())).thenReturn(true);
//
//        Assertions.assertThrows(InvalidRequestException.class, () -> { sut5.create(message);});
//        verify(mockMessageDao, times(0)).save(message);
//    }


    // Chat
    @Test
    public void test_validInput_givenChat_returnFalse(){

        Chat chat = new Chat("1",stu, "","6-11-2022", "Bob");


        boolean actualResult = sut6.validateInput(chat);

        Assertions.assertFalse(actualResult);
    }

    @Test
    public void test_create_givenRepeatedChatInformation_throwsInvalidRequestException(){
        Chat chat = new Chat("1",stu, "Mission Secrets","", "Bob");
        when(mockChatDao.existsById(chat.getId())).thenReturn(true);

        Assertions.assertThrows(InvalidRequestException.class, () -> { sut6.create(chat);});
        verify(mockChatDao, times(0)).save(chat);
    }
}
