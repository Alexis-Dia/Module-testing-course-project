1. In the project csrf(https://habr.com/ru/post/264641/) is disabled using:
			.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
2. For converting from entities to dto uses this way:
        return journalRepository.findAll(***, ***).map(DtoMapper::toJournalDto);			
				