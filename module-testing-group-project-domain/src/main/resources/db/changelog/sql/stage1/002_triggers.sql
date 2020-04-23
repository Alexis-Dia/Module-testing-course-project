go
if not exists (select * from sysobjects where name='audit_report_data' and xtype='U')
CREATE TABLE [carriages_system].[dbo].[audit_report_data](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idOld] INT,
	[idNew] INT,
	[recdate] SMALLDATETIME,
	[departureOld] SMALLDATETIME,
	[departureNew] SMALLDATETIME ,
	[weightOld] DECIMAL(18, 2),
	[weightNew] DECIMAL(18, 2) ,
	[distanceOld] DECIMAL(18, 2),
	[distanceNew] DECIMAL(18, 2) ,
	[arrivalNew] SMALLDATETIME,
	[arrivalOld] SMALLDATETIME,
	CONSTRAINT [audit_reports] PRIMARY KEY CLUSTERED
	(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


--1. Триггер аудита изменения отчетов
GO
CREATE Trigger auditReport ON [dbo].[report] AFTER
UPDATE, DELETE
AS
BEGIN
DECLARE @idOld INT, @idNew INT
DECLARE @carIdOld INT, @carIdNew INT
DECLARE @driverIdOld INT, @driverIdNew INT
DECLARE @departureOld SMALLDATETIME, @departureNew SMALLDATETIME
DECLARE @weightOld DECIMAL(18, 2), @weightNew DECIMAL(18, 2)
DECLARE @distanceOld DECIMAL(18, 2), @distanceNew DECIMAL(18, 2)
DECLARE @arrivalOld SMALLDATETIME, @arrivalNew SMALLDATETIME
SET @idOld = (select [id] from deleted)
SET @idNew = (select [id] from inserted)
SET @departureOld = (select [departure] from deleted)
SET @departureNew = (select [departure] from inserted)
SET @weightOld = (select [weight] from deleted)
SET @weightNew = (select [weight] from inserted)
SET @distanceOld = (select [distance] from deleted)
SET @distanceNew = (select [distance] from inserted)
SET @arrivalOld = (select [arrival] from deleted)
SET @arrivalNew = (select [arrival] from inserted);
INSERT INTO [carriages_system].[dbo].[audit_report_data] VALUES
(@idOld, @idNew, GETDATE(), @departureOld, @departureNew,
@weightOld, @weightNew, @distanceOld, @distanceNew, @arrivalOld, @arrivalNew)
END

--DROP TRIGGER auditReport

--UPDATE [dbo].[report]
--SET [arrival] = '2020-01-30 18:59:00'
--WHERE [id] = 43


--2. Триггер для корректировки ввода даты
GO
CREATE TRIGGER checkArrival ON [dbo].[report] AFTER INSERT
AS
BEGIN
DECLARE @insertedId INT
DECLARE @departure SMALLDATETIME
DECLARE @arrival SMALLDATETIME
SET @insertedId = (SELECT id FROM inserted)
SET @departure = (SELECT departure FROM inserted)
SET @arrival = (SELECT arrival FROM inserted)
if @departure > @arrival
begin
UPDATE [dbo].[report]
SET arrival = @departure WHERE id = @insertedId
end
END


--USE carriages_system
--GO
--INSERT INTO [dbo].[report]([departure],[weight],[distance],[arrival])
--     VALUES ('2022-11-15 11:00:00.000',399.0,199.0,'2021-11-17 11:00:00.000')
--GO

--3. Триггер для невозможности добавления отчета в день дальнобойщика - 08.31.2019
GO
CREATE TRIGGER checkDeparture ON [dbo].[report]
AFTER INSERT, UPDATE
AS
BEGIN
DECLARE @departure SMALLDATETIME
SET @departure = (SELECT departure FROM inserted)
IF (8 = datepart(m, @departure) AND 31 = datepart(d, @departure))
BEGIN
ROLLBACK TRANSACTION
PRINT 'День дальнобойщика, вы не можете задавать в БД запись на этот день!'
END
ELSE PRINT 'Строка вставлена/изменена'
END
