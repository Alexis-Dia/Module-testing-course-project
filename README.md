1. Когда проект запускается впервые, то нужно создать  руками новую БД с именем  -  'carriages_system' и
 указать юезера - root
2. Что бы появились диаграммы - надо кликнуть на Database Diagram -> New Data Database Diagram.
При attach базы - Microsoft SQL Server Manager Studio - нужно запускать под администратором.
2. In the project csrf(https://habr.com/ru/post/264641/) is disabled using:
			.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
3. For converting from entities to dto uses this way:
        return journalRepository.findAll(***, ***).map(DtoMapper::toJournalDto);		
4. Remove from generated script all this mentions:        
    CREATE DATABASE        
    DISABLE_BROKER	
    ATE_CORRELATION_OPTIMIZAT
    FILESTREAM
    GO
    USE [master]
    GO
    ALTER DATABASE [carriages_system] SET  READ_WRITE 
    DATABASECHANGELOG
    DATABASECHANGELOGLOCK

Password for admin - admin
Password for any driver - user

    task:
    
        POST http://localhost:8080/task/createNew
                {
                "summaryDistance": 469.1,
                "weight": 231.4,
                "driver": {"userID": null},
                "car": {"id": 4},
                "taskStatus": "FREE",
                "name": "Baranovichi-Svetlogorsk",
                "reward": 389
                }
            {
            "summaryDistance": 469.1,
            "weight": 231.4,
            "driver": {"userID": 1},
            "car": {"id": 4},
            "taskStatus": "FREE",
            "name": "Baranovichi-Svetlogorsk",
            "reward": 389
            }
        
        report:
        
            POST http://localhost:8080/report/getByTaskId?taskId=3
            
            @PostMapping("/{taskId}/createReport")
            {
                "departure": "2019-11-27 11:00:00.000",
                "weight": 111.1,
                "distance": 311.1,
                "arrival": "2019-11-27 17:00:00.000"
            }
        
        car:
        
            http://localhost:8080/car/addNew
            @PostMapping("/addNew")
            {{
                                 "id": "5"
                             },
                "brand": 
                "year": "2009-01-17 17:00:00.000",
                "number": "FJ-3351",
                "dateOfReceipt": "2019-11-27 17:00:00.000",
                "carStatus": "FREE"
            }
            
            http://localhost:8080/car/edit
            @PostMapping("/edit")
            {
                "id":"4",
                "brand": {
                    "id": "15"
                },
                "year": "2009-01-17dasda 17:00:00.000",
                "number": "FJ-3352",
                "dateOfReceipt": "2019-11-27 17:00:00.000",
                "carStatus": "FREE"
            }
            
            http://localhost:8080/car/removeById?carId=10
            @PostMapping("/removeById")
        
        
        user:
        
            http://localhost:8080/user/allDrivers
            @PostMapping("/allDrivers")
                
            http://localhost:8080/user/getById?id=11
            @PostMapping("/getById")
        
            @PostMapping("/getAdmin")
            http://localhost:8080/user/getAdmin
        
            http://localhost:8080/user/edit
            @PostMapping("/edit")
            {
                "userID":"9",
                "lastName": "Mahrez",
                "firstName": "Riad",
                "patronymic": "Abulovich",
                "birthday": "1979-11-27 17:00:00.000",
                "emailAddress": "Kon@tut.by",
                "password": "12345678",
                "money": 100.9
            }
        
            http://localhost:8080/user/changeStatus
            @PostMapping("/changeStatus")
            {
                "userID":"9",
                "lastName": "Mahrez",
                "firstName": "Riad",
                "patronymic": "Abulovich",
                "birthday": "1979-11-27 17:00:00.000",
                "emailAddress": "Kon@tut.by",
                "password": "12345678",
                "money": 100.9,
                "userStatus": "BUSY"
            }
        
            http://localhost:8080/user/new
            @PostMapping("/new")
            {
                "lastName": "Mahrez",
                "firstName": "Riad",
                "patronymic": "Abulovich",
                "birthday": "1979-11-27 17:00:00.000",
                "emailAddress": "Kon@tut.by",
                "password": "12345678"
            }
            
            http://localhost:8080/user/transferMoney?userId=2&money=100.0
            @PostMapping("/transferMoney")
            
            
        https://coderoad.ru/6305801/Spring-SecurityContext-%D0%B2%D0%BE%D0%B7%D0%B2%D1%80%D0%B0%D1%82-null-%D0%B0%D1%83%D1%82%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D0%B8-%D0%BD%D0%B0-%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0%D1%85-%D0%BE%D1%88%D0%B8%D0%B1%D0%BE%D0%BA
        
        
        import org.springframework.security.authentication.*;
        import org.springframework.security.core.*;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.context.SecurityContextHolder;
        
        public class AuthenticationExample {
        private static AuthenticationManager am = new SampleAuthenticationManager();
        
        public static void main(String[] args) throws Exception {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
            while(true) {
            System.out.println("Please enter your username:");
            String name = in.readLine();
            System.out.println("Please enter your password:");
            String password = in.readLine();
            try {
                Authentication request = new UsernamePasswordAuthenticationToken(name, password);
                Authentication result = am.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            } catch(AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
            }
            }
            System.out.println("Successfully authenticated. Security context contains: " +
                    SecurityContextHolder.getContext().getAuthentication());
        }
        }
        
        class SampleAuthenticationManager implements AuthenticationManager {
        static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
        
        static {
            AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        public Authentication authenticate(Authentication auth) throws AuthenticationException {
            if (auth.getName().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                auth.getCredentials(), AUTHORITIES);
            }
            throw new BadCredentialsException("Bad Credentials");
        }
        }
       
Artickle about adding oAuth2.0:        
https://javarush.ru/groups/posts/2269-druzhim-obihchnihy-vkhod-cherez-email-i-oauth2-v-spring-security-na-primere-servisa-zametok#%D0%A1%D1%83%D1%89%D0%BD%D0%BE%D1%81%D1%82%D0%B8
In this article there is a helpful comment how to check performance:

        long before = System.currentTimeMillis();
        repository.save(new Entity());
        repository.findAll().forEach(System.out::println);
        repository.findAll().forEach(System.out::println);
        repository.findAll().forEach(System.out::println);
        repository.findAll().forEach(System.out::println);
        repository.save(new Entity());
        repository.save(new Entity());
        repository.save(new Entity());
        repository.findAll().forEach(System.out::println);
        long after = System.currentTimeMillis();

        System.out.println(after-before);

        before = System.currentTimeMillis();
        service.check();
        after = System.currentTimeMillis();

        System.out.println(after-before);

How to inject test repository to test service articles:        
https://www.baeldung.com/spring-boot-testing
https://hellokoding.com/spring-boot-test-service-layer-example-with-mockitos-mock-and-injectmock/    
https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockito-junit-example/
https://dzone.com/articles/unit-testing-java-streams-and-lambdas
        
Ещё насчёт пропуска сервисного слоя: нежелательно так делать еще по той причине, что именно на уровне сервисов должны управляться транзакции, а так транзакции в
 репозитории работают по auto-commit=true считай (на самом деле флажок в JDBC не стоит, но реализация репов спринговских как раз такая), ведь каждый метод репозитория - 
 отдельная транзакция (репы помечены по-умолчанию @Transactional аннотациями, класс JpaRepository так же обладает @Transactional(readonly=true) аннотацией, 
 которая наследуется, потому объявленные методы в наших репах, унаследованных от JpaRepository, по чтению работают, ведь неявно помечены @Transactional,
  однако если попробовать объявить метод удаления или изменения, а потом воспользоваться им, у нас выбьет ошибка, что транзакция не открыта.

Так же если мы рассчитывали изменить поля вычитанной сущности и чтобы они изменились в БД, как и работает ORM, нас ждет разочарование, ведь после чтения, транзакция уже закоммичена,
 а значит сущность перешла в состояние Detached, и изменения сущности не отправятся в БД.

Потому транзакциями нужно управлять вручную. Слишком много капканов, на которые можно встать. Ситуация ещё сильнее усугубляется, когда есть сущность со связями с другими сущностями, 
с LAZY связями. Если мы вычитали эту сущность через репо. а потом попробовали получить доступ к LAZY сущности внутри этой сущности, мы падаем с ошибкой LazyInitializationException.