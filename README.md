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
            {
                "brand": {
                    "id": "5"
                },
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
        
        
        
        
        
        
        
        
        