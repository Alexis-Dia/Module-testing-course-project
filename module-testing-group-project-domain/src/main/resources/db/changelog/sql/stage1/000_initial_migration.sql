USE [master]
GO
/****** Object:  Database [carriages_system]    Script Date: 2/14/2020 2:57:12 PM ******/
ALTER DATABASE [carriages_system] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [carriages_system].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [carriages_system] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [carriages_system] SET ANSI_NULLS OFF
GO
ALTER DATABASE [carriages_system] SET ANSI_PADDING OFF
GO
ALTER DATABASE [carriages_system] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [carriages_system] SET ARITHABORT OFF
GO
ALTER DATABASE [carriages_system] SET AUTO_CLOSE ON
GO
ALTER DATABASE [carriages_system] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [carriages_system] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [carriages_system] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [carriages_system] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [carriages_system] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [carriages_system] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [carriages_system] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [carriages_system] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [carriages_system] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [carriages_system] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [carriages_system] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [carriages_system] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [carriages_system] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [carriages_system] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [carriages_system] SET RECOVERY SIMPLE
GO
ALTER DATABASE [carriages_system] SET  MULTI_USER
GO
ALTER DATABASE [carriages_system] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [carriages_system] SET DB_CHAINING OFF
GO
ALTER DATABASE [carriages_system] SET TARGET_RECOVERY_TIME = 0 SECONDS
GO
ALTER DATABASE [carriages_system] SET DELAYED_DURABILITY = DISABLED
GO
USE [carriages_system]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[brand](
	[id] [int] NOT NULL,
	[brand] [varchar](250) NOT NULL,
	[carrying_capacity] [float] NOT NULL,
	[model] [varchar](250) NULL,
 CONSTRAINT [PK_brand] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[car]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[car](
	[id] [int] NOT NULL,
	[brand_id] [int] NOT NULL,
	[year] [date] NOT NULL,
	[number] [varchar](50) NOT NULL,
	[date_of_receipt] [date] NOT NULL,
	[status_id] [int] NOT NULL,
 CONSTRAINT [PK_car] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[car_status]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[car_status](
	[id] [int] NOT NULL,
	[name] [varchar](100) NOT NULL,
 CONSTRAINT [PK_car_status] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DATABASECHANGELOG]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Object:  Table [dbo].[DATABASECHANGELOGLOCK]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON

GO
/****** Object:  Table [dbo].[long_report]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[long_report](
	[id] [int] NOT NULL,
	[carId] [int] NULL,
	[driver_id] [int] NULL,
	[departure] [datetime] NULL,
	[weight] [decimal](18, 0) NULL,
	[distance] [decimal](18, 0) NULL,
	[arrival] [datetime] NULL,
	[departure_date] [varchar](250) NULL,
	[departure_time] [varchar](250) NULL,
	[arrival_date] [varchar](250) NULL,
	[arrival_time] [varchar](250) NULL,
 CONSTRAINT [PK_long_journey] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[report]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[report](
	[id] [int] NOT NULL,
	[task_id] [int] NOT NULL,
	[departure] [datetime] NOT NULL,
	[weight] [float] NOT NULL,
	[distance] [float] NOT NULL,
	[arrival] [datetime] NOT NULL,
 CONSTRAINT [PK_journal] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[role]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[security_level] [int] NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[task]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[task](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[summary_distance] [float] NOT NULL,
	[weight] [float] NOT NULL,
	[driver_id] [int] NULL,
	[car_id] [int] NULL,
	[status_id] [int] NOT NULL CONSTRAINT [DF_task_status_id]  DEFAULT ((1)),
	[reward] [float] NOT NULL,
	[name] [varchar](250) NULL,
 CONSTRAINT [PK_task] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[task_status]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[task_status](
	[id] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_task_status] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[user]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[last_name] [varchar](250) NOT NULL,
	[first_name] [varchar](250) NOT NULL,
	[patronymic] [varchar](250) NOT NULL,
	[birthday] [datetime] NOT NULL,
	[login] [varchar](250) NOT NULL,
	[password] [varchar](250) NOT NULL,
	[money] [float] NOT NULL,
	[role_id] [int] NOT NULL,
	[status_id] [int] NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[user_status]    Script Date: 2/14/2020 2:57:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user_status](
	[id] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_driver_status] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[brand] ([id], [brand], [carrying_capacity], [model]) VALUES (2, N'VW', 3540.5, N'Crafter')
INSERT [dbo].[brand] ([id], [brand], [carrying_capacity], [model]) VALUES (3, N'VW', 1240.5, N'Caddy')
INSERT [dbo].[brand] ([id], [brand], [carrying_capacity], [model]) VALUES (4, N'Ford', 1240.5, N'Transit')
INSERT [dbo].[brand] ([id], [brand], [carrying_capacity], [model]) VALUES (5, N'Ford', 4500, N'F650')
INSERT [dbo].[brand] ([id], [brand], [carrying_capacity], [model]) VALUES (6, N'Scania', 18000, N'R500')
INSERT [dbo].[car] ([id], [brand_id], [year], [number], [date_of_receipt], [status_id]) VALUES (4, 4, CAST(N'1999-06-23' AS Date), N'OP-1871', CAST(N'2001-06-23' AS Date), 1)
INSERT [dbo].[car] ([id], [brand_id], [year], [number], [date_of_receipt], [status_id]) VALUES (5, 3, CAST(N'2006-06-23' AS Date), N'YZ-5643', CAST(N'2008-06-23' AS Date), 1)
INSERT [dbo].[car] ([id], [brand_id], [year], [number], [date_of_receipt], [status_id]) VALUES (6, 2, CAST(N'1999-06-23' AS Date), N'HT-8381', CAST(N'2001-06-23' AS Date), 1)
INSERT [dbo].[car] ([id], [brand_id], [year], [number], [date_of_receipt], [status_id]) VALUES (7, 5, CAST(N'2019-03-11' AS Date), N'JZ-4915', CAST(N'2021-03-11' AS Date), 1)
INSERT [dbo].[car] ([id], [brand_id], [year], [number], [date_of_receipt], [status_id]) VALUES (8, 6, CAST(N'2007-10-07' AS Date), N'AA-1357', CAST(N'2009-10-07' AS Date), 1)
INSERT [dbo].[car_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[car_status] ([id], [name]) VALUES (2, N'BUSY')
INSERT [dbo].[long_report] ([id], [carId], [driver_id], [departure], [weight], [distance], [arrival], [departure_date], [departure_time], [arrival_date], [arrival_time]) VALUES (1, 4, 1, CAST(N'2019-12-30 11:00:00.000' AS DateTime), CAST(1345 AS Decimal(18, 0)), CAST(1528 AS Decimal(18, 0)), CAST(N'2020-01-07 06:00:00.000' AS DateTime), N'2019-08-27', N'07:00:00.0000000', N'2020-01-07', N'06:00:00.0000000')
INSERT [dbo].[long_report] ([id], [carId], [driver_id], [departure], [weight], [distance], [arrival], [departure_date], [departure_time], [arrival_date], [arrival_time]) VALUES (2, 6, 5, CAST(N'2019-10-01 07:00:00.000' AS DateTime), CAST(2500 AS Decimal(18, 0)), CAST(400 AS Decimal(18, 0)), CAST(N'2019-10-11 14:00:00.000' AS DateTime), N'2019-10-05', N'07:00:00.0000000', N'2019-10-11', N'14:00:00.0000000')
INSERT [dbo].[long_report] ([id], [carId], [driver_id], [departure], [weight], [distance], [arrival], [departure_date], [departure_time], [arrival_date], [arrival_time]) VALUES (3, 6, 5, CAST(N'2019-10-07 07:00:00.000' AS DateTime), CAST(2900 AS Decimal(18, 0)), CAST(2111 AS Decimal(18, 0)), CAST(N'2019-10-15 14:00:00.000' AS DateTime), N'2019-10-07', N'07:00:00.0000000', N'2019-10-15', N'14:00:00.0000000')
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (1, 1, CAST(N'2019-11-27 11:00:00.000' AS DateTime), 256, 128, CAST(N'2019-11-27 19:30:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (2, 1, CAST(N'2019-11-28 11:00:00.000' AS DateTime), 1256, 1228, CAST(N'2019-11-29 06:10:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (3, 1, CAST(N'2008-02-25 11:00:00.000' AS DateTime), 351, 567, CAST(N'2008-02-26 11:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (4, 1, CAST(N'2009-02-22 11:00:00.000' AS DateTime), 341, 769, CAST(N'2009-02-23 11:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (6, 1, CAST(N'2019-12-30 11:00:00.000' AS DateTime), 1345, 1528, CAST(N'2020-01-07 06:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (7, 1, CAST(N'2019-08-17 07:00:00.000' AS DateTime), 1045, 728, CAST(N'2019-08-17 15:20:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (8, 1, CAST(N'2019-08-27 07:00:00.000' AS DateTime), 700, 1000, CAST(N'2019-08-27 14:20:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (9, 1, CAST(N'2019-09-11 07:00:00.000' AS DateTime), 1100, 900, CAST(N'2019-09-11 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (10, 1, CAST(N'2019-09-15 07:00:00.000' AS DateTime), 1100, 900, CAST(N'2019-09-15 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (12, 1, CAST(N'2019-10-01 07:00:00.000' AS DateTime), 2500, 400, CAST(N'2019-10-11 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (13, 1, CAST(N'2019-10-05 07:00:00.000' AS DateTime), 2700, 500, CAST(N'2019-10-05 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (14, 1, CAST(N'2019-10-07 07:00:00.000' AS DateTime), 2900, 2111, CAST(N'2019-10-15 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (15, 1, CAST(N'2019-10-09 07:00:00.000' AS DateTime), 2900, 450, CAST(N'2019-10-09 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (16, 1, CAST(N'2020-01-03 07:00:00.000' AS DateTime), 345, 567, CAST(N'2020-01-03 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (17, 1, CAST(N'2020-01-04 07:00:00.000' AS DateTime), 547, 234, CAST(N'2020-01-04 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (18, 1, CAST(N'2020-01-05 07:00:00.000' AS DateTime), 447, 434, CAST(N'2020-01-05 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (19, 1, CAST(N'2020-01-02 07:00:00.000' AS DateTime), 876, 1110, CAST(N'2020-01-02 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (20, 2, CAST(N'2020-01-05 07:00:00.000' AS DateTime), 511, 1300, CAST(N'2020-01-05 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (21, 2, CAST(N'2020-01-06 07:00:00.000' AS DateTime), 3900, 455, CAST(N'2020-01-06 18:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (22, 2, CAST(N'2019-12-17 07:00:00.000' AS DateTime), 456, 321, CAST(N'2019-12-17 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (23, 2, CAST(N'2020-01-06 07:00:00.000' AS DateTime), 491, 125, CAST(N'2020-01-06 18:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (24, 2, CAST(N'2020-01-07 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-07 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (25, 2, CAST(N'2020-01-08 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-08 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (26, 2, CAST(N'2020-01-09 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-09 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (27, 3, CAST(N'2020-01-10 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-10 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (28, 3, CAST(N'2020-01-11 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-11 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (29, 3, CAST(N'2020-01-12 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-12 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (30, 3, CAST(N'2020-01-13 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-13 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (31, 3, CAST(N'2020-01-14 07:00:00.000' AS DateTime), 500, 500, CAST(N'2020-01-14 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (32, 3, CAST(N'2020-01-14 07:00:00.000' AS DateTime), 100, 100, CAST(N'2020-01-14 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (33, 3, CAST(N'2020-01-15 07:00:00.000' AS DateTime), 100, 100, CAST(N'2020-01-15 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (34, 3, CAST(N'2020-01-16 07:00:00.000' AS DateTime), 100, 100, CAST(N'2020-01-16 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (35, 3, CAST(N'2020-01-17 07:00:00.000' AS DateTime), 100, 700, CAST(N'2020-01-17 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (36, 3, CAST(N'2020-01-18 07:00:00.000' AS DateTime), 100, 750, CAST(N'2020-01-18 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (37, 3, CAST(N'2020-01-19 07:00:00.000' AS DateTime), 100, 750, CAST(N'2020-01-19 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (38, 3, CAST(N'2020-01-20 07:00:00.000' AS DateTime), 100, 750, CAST(N'2020-01-20 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (39, 3, CAST(N'2020-01-21 07:00:00.000' AS DateTime), 100, 250, CAST(N'2020-01-21 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (40, 3, CAST(N'2020-01-22 07:00:00.000' AS DateTime), 100, 250, CAST(N'2020-01-22 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (41, 3, CAST(N'2020-01-23 07:00:00.000' AS DateTime), 100, 250, CAST(N'2020-01-23 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (42, 3, CAST(N'2020-01-24 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-24 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (43, 3, CAST(N'2020-01-25 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-25 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (44, 3, CAST(N'2020-01-26 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-26 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (45, 3, CAST(N'2020-01-27 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-27 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (46, 3, CAST(N'2020-01-28 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-28 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (47, 3, CAST(N'2020-01-29 07:00:00.000' AS DateTime), 100, 850, CAST(N'2020-01-29 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (48, 3, CAST(N'2020-01-31 07:00:00.000' AS DateTime), 100, 399, CAST(N'2020-01-31 17:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[role] ON

INSERT [dbo].[role] ([id], [name], [security_level]) VALUES (1, N'ADMIN', 1)
INSERT [dbo].[role] ([id], [name], [security_level]) VALUES (2, N'DRIVER', 2)
SET IDENTITY_INSERT [dbo].[role] OFF
SET IDENTITY_INSERT [dbo].[task] ON

INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward], [name]) VALUES (1, 7500, 511, 1, 4, 1, 2000, NULL)
INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward], [name]) VALUES (3, 3790.9, 1200.1, 2, 5, 1, 1150, NULL)
INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward], [name]) VALUES (4, 2310.9, 800.1, 5, 6, 1, 780, NULL)
SET IDENTITY_INSERT [dbo].[task] OFF
INSERT [dbo].[task_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[task_status] ([id], [name]) VALUES (2, N'IN PROCESS')
INSERT [dbo].[task_status] ([id], [name]) VALUES (3, N'VALIDATING')
INSERT [dbo].[task_status] ([id], [name]) VALUES (4, N'REJECTED')
INSERT [dbo].[task_status] ([id], [name]) VALUES (5, N'FINISHED')
SET IDENTITY_INSERT [dbo].[user] ON

INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (1, N'Ivanov', N'Ivan', N'Ivanovich', CAST(N'1989-01-13 00:00:00.000' AS DateTime), N'ivanov', N'ivanov', 0, 2, 1)
INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (2, N'Sidorov', N'Igor', N'Igorevich', CAST(N'1973-02-03 00:00:00.000' AS DateTime), N'sidorov', N'sidorov', 0, 2, 1)
INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (5, N'Novikov', N'Maxim', N'Nikolaevich', CAST(N'1991-12-27 00:00:00.000' AS DateTime), N'novikov', N'novikov', 0, 2, 1)
INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (7, N'Ivanov', N'Afanasiy', N'Konstantinovich', CAST(N'1978-06-05 00:00:00.000' AS DateTime), N'ivanov', N'ivanov', 0, 2, 1)
INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (9, N'Vasilev', N'Arkadiy', N'Arkkadievich', CAST(N'1923-04-04 00:00:00.000' AS DateTime), N'vasiliev', N'vasiliev', 0, 2, 1)
INSERT [dbo].[user] ([id], [last_name], [first_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id]) VALUES (13, N'Admin', N'Admin', N'Admin', CAST(N'1990-04-04 00:00:00.000' AS DateTime), N'admin', N'admin', 1000000, 1, 1)
SET IDENTITY_INSERT [dbo].[user] OFF
INSERT [dbo].[user_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[user_status] ([id], [name]) VALUES (2, N'BUSY')
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_brand] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brand] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_brand]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_car_status] FOREIGN KEY([status_id])
REFERENCES [dbo].[car_status] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_car_status]
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD  CONSTRAINT [FK_task_car] FOREIGN KEY([car_id])
REFERENCES [dbo].[car] ([id])
GO
ALTER TABLE [dbo].[task] CHECK CONSTRAINT [FK_task_car]
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD  CONSTRAINT [FK_task_task_status] FOREIGN KEY([status_id])
REFERENCES [dbo].[task_status] ([id])
GO
ALTER TABLE [dbo].[task] CHECK CONSTRAINT [FK_task_task_status]
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD  CONSTRAINT [FK_task_user] FOREIGN KEY([driver_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[task] CHECK CONSTRAINT [FK_task_user]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_role]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_user_status] FOREIGN KEY([status_id])
REFERENCES [dbo].[user_status] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_user_status]
GO

