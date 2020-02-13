1. Когда проект запускается впервые, то нужно создать  руками новую БД с именем  -  'carriages_system' и
 указать юезера - root
2. Что бы появились диаграммы - надо кликнуть на Database Diagram -> New Data Database Diagram.
При attach базы - Microsoft SQL Server Manager Studio - нужно запускать под администратором.
2. In the project csrf(https://habr.com/ru/post/264641/) is disabled using:
			.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
3. For converting from entities to dto uses this way:
        return journalRepository.findAll(***, ***).map(DtoMapper::toJournalDto);			
				