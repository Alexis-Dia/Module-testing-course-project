USE [master]
GO
ALTER DATABASE [cariages_system] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [cariages_system].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [cariages_system] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [cariages_system] SET ANSI_NULLS OFF
GO
ALTER DATABASE [cariages_system] SET ANSI_PADDING OFF
GO
ALTER DATABASE [cariages_system] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [cariages_system] SET ARITHABORT OFF
GO
ALTER DATABASE [cariages_system] SET AUTO_CLOSE ON
GO
ALTER DATABASE [cariages_system] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [cariages_system] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [cariages_system] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [cariages_system] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [cariages_system] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [cariages_system] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [cariages_system] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [cariages_system] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [cariages_system] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [cariages_system] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [cariages_system] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [cariages_system] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [cariages_system] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [cariages_system] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [cariages_system] SET RECOVERY SIMPLE
GO
ALTER DATABASE [cariages_system] SET  MULTI_USER
GO
ALTER DATABASE [cariages_system] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [cariages_system] SET DB_CHAINING OFF
GO
ALTER DATABASE [cariages_system] SET TARGET_RECOVERY_TIME = 0 SECONDS
GO
ALTER DATABASE [cariages_system] SET DELAYED_DURABILITY = DISABLED
GO
USE [cariages_system]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand](
	[id] [int] NOT NULL,
	[brand] [nvarchar](max) NOT NULL,
	[cariagyingCapacity] [float] NOT NULL,
	[model] [nvarchar](max) NULL,
 CONSTRAINT [PK_brand] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[car]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[car](
	[id] [int] NOT NULL,
	[brandId] [int] NOT NULL,
	[year] [datetime] NOT NULL,
	[number] [nvarchar](max) NOT NULL,
	[shortYear] [int] NULL,
	[yearOfReceipt] [datetime] NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [PK_car] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[car_status]    Script Date: 12.02.2020 14:01:55 ******/
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
/****** Object:  Table [dbo].[long_report]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[long_report](
	[id] [int] NOT NULL,
	[carId] [int] NULL,
	[driverId] [int] NULL,
	[departure] [datetime] NULL,
	[weight] [decimal](18, 0) NULL,
	[distance] [decimal](18, 0) NULL,
	[arrival] [datetime] NULL,
	[departureDate] [nvarchar](max) NULL,
	[departureTime] [nvarchar](max) NULL,
	[arrivalDate] [nvarchar](max) NULL,
	[arrivalTime] [nvarchar](max) NULL,
 CONSTRAINT [PK_long_journey] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[report]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[report](
	[id] [int] NOT NULL,
	[task_id] [int] NOT NULL,
	[departure] [datetime] NOT NULL,
	[weight] [decimal](18, 0) NOT NULL,
	[distance] [decimal](18, 0) NOT NULL,
	[arrival] [datetime] NOT NULL,
 CONSTRAINT [PK_journal] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[role]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[task]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[task](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[summary_distance] [float] NOT NULL,
	[weight] [float] NOT NULL,
	[driver_id] [int] NULL,
	[car_id] [int] NULL,
	[status_id] [int] NOT NULL CONSTRAINT [DF_task_status_id]  DEFAULT ((1)),
	[reward] [float] NOT NULL,
 CONSTRAINT [PK_task] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[task_status]    Script Date: 12.02.2020 14:01:55 ******/
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
/****** Object:  Table [dbo].[user]    Script Date: 12.02.2020 14:01:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[lastName] [nvarchar](max) NOT NULL,
	[firstName] [nvarchar](max) NOT NULL,
	[patronymic] [nvarchar](max) NOT NULL,
	[birthDay] [datetime] NOT NULL,
	[login] [nvarchar](max) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[money] [float] NOT NULL,
	[roleId] [int] NOT NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[user_status]    Script Date: 12.02.2020 14:01:55 ******/
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
INSERT [dbo].[brand] ([id], [brand], [cariagyingCapacity], [model]) VALUES (2, N'VW', 3540.5, N'Crafter')
INSERT [dbo].[brand] ([id], [brand], [cariagyingCapacity], [model]) VALUES (3, N'VW', 1240.5, N'Caddy')
INSERT [dbo].[brand] ([id], [brand], [cariagyingCapacity], [model]) VALUES (4, N'Ford', 1240.5, N'Transit')
INSERT [dbo].[brand] ([id], [brand], [cariagyingCapacity], [model]) VALUES (5, N'Ford', 4500, N'F650')
INSERT [dbo].[brand] ([id], [brand], [cariagyingCapacity], [model]) VALUES (6, N'Scania', 18000, N'R500')
INSERT [dbo].[car] ([id], [brandId], [year], [number], [shortYear], [yearOfReceipt], [statusId]) VALUES (4, 4, CAST(N'1999-06-23 00:00:00.000' AS DateTime), N'OP-1871', 1999, CAST(N'2001-06-23 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[car] ([id], [brandId], [year], [number], [shortYear], [yearOfReceipt], [statusId]) VALUES (5, 3, CAST(N'2006-06-23 00:00:00.000' AS DateTime), N'YZ-5643', 2006, CAST(N'2008-06-23 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[car] ([id], [brandId], [year], [number], [shortYear], [yearOfReceipt], [statusId]) VALUES (6, 2, CAST(N'1999-06-23 00:00:00.000' AS DateTime), N'HT-8381', 1999, CAST(N'2001-06-23 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[car] ([id], [brandId], [year], [number], [shortYear], [yearOfReceipt], [statusId]) VALUES (7, 5, CAST(N'2019-03-11 00:00:00.000' AS DateTime), N'JZ-4915', 2019, CAST(N'2021-03-11 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[car] ([id], [brandId], [year], [number], [shortYear], [yearOfReceipt], [statusId]) VALUES (8, 6, CAST(N'2007-10-07 00:00:00.000' AS DateTime), N'AA-1357', 2007, CAST(N'2009-10-07 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[car_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[car_status] ([id], [name]) VALUES (2, N'BUSY')
INSERT [dbo].[long_report] ([id], [carId], [driverId], [departure], [weight], [distance], [arrival], [departureDate], [departureTime], [arrivalDate], [arrivalTime]) VALUES (1, 4, 1, CAST(N'2019-12-30 11:00:00.000' AS DateTime), CAST(1345 AS Decimal(18, 0)), CAST(1528 AS Decimal(18, 0)), CAST(N'2020-01-07 06:00:00.000' AS DateTime), N'2019-08-27', N'07:00:00.0000000', N'2020-01-07', N'06:00:00.0000000')
INSERT [dbo].[long_report] ([id], [carId], [driverId], [departure], [weight], [distance], [arrival], [departureDate], [departureTime], [arrivalDate], [arrivalTime]) VALUES (2, 6, 5, CAST(N'2019-10-01 07:00:00.000' AS DateTime), CAST(2500 AS Decimal(18, 0)), CAST(400 AS Decimal(18, 0)), CAST(N'2019-10-11 14:00:00.000' AS DateTime), N'2019-10-05', N'07:00:00.0000000', N'2019-10-11', N'14:00:00.0000000')
INSERT [dbo].[long_report] ([id], [carId], [driverId], [departure], [weight], [distance], [arrival], [departureDate], [departureTime], [arrivalDate], [arrivalTime]) VALUES (3, 6, 5, CAST(N'2019-10-07 07:00:00.000' AS DateTime), CAST(2900 AS Decimal(18, 0)), CAST(2111 AS Decimal(18, 0)), CAST(N'2019-10-15 14:00:00.000' AS DateTime), N'2019-10-07', N'07:00:00.0000000', N'2019-10-15', N'14:00:00.0000000')
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (1, 1, CAST(N'2019-11-27 11:00:00.000' AS DateTime), CAST(256 AS Decimal(18, 0)), CAST(128 AS Decimal(18, 0)), CAST(N'2019-11-27 19:30:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (2, 1, CAST(N'2019-11-28 11:00:00.000' AS DateTime), CAST(1256 AS Decimal(18, 0)), CAST(1228 AS Decimal(18, 0)), CAST(N'2019-11-29 06:10:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (3, 1, CAST(N'2008-02-25 11:00:00.000' AS DateTime), CAST(351 AS Decimal(18, 0)), CAST(567 AS Decimal(18, 0)), CAST(N'2008-02-26 11:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (4, 1, CAST(N'2009-02-22 11:00:00.000' AS DateTime), CAST(341 AS Decimal(18, 0)), CAST(769 AS Decimal(18, 0)), CAST(N'2009-02-23 11:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (6, 1, CAST(N'2019-12-30 11:00:00.000' AS DateTime), CAST(1345 AS Decimal(18, 0)), CAST(1528 AS Decimal(18, 0)), CAST(N'2020-01-07 06:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (7, 1, CAST(N'2019-08-17 07:00:00.000' AS DateTime), CAST(1045 AS Decimal(18, 0)), CAST(728 AS Decimal(18, 0)), CAST(N'2019-08-17 15:20:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (8, 1, CAST(N'2019-08-27 07:00:00.000' AS DateTime), CAST(700 AS Decimal(18, 0)), CAST(1000 AS Decimal(18, 0)), CAST(N'2019-08-27 14:20:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (9, 1, CAST(N'2019-09-11 07:00:00.000' AS DateTime), CAST(1100 AS Decimal(18, 0)), CAST(900 AS Decimal(18, 0)), CAST(N'2019-09-11 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (10, 1, CAST(N'2019-09-15 07:00:00.000' AS DateTime), CAST(1100 AS Decimal(18, 0)), CAST(900 AS Decimal(18, 0)), CAST(N'2019-09-15 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (12, 1, CAST(N'2019-10-01 07:00:00.000' AS DateTime), CAST(2500 AS Decimal(18, 0)), CAST(400 AS Decimal(18, 0)), CAST(N'2019-10-11 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (13, 1, CAST(N'2019-10-05 07:00:00.000' AS DateTime), CAST(2700 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2019-10-05 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (14, 1, CAST(N'2019-10-07 07:00:00.000' AS DateTime), CAST(2900 AS Decimal(18, 0)), CAST(2111 AS Decimal(18, 0)), CAST(N'2019-10-15 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (15, 1, CAST(N'2019-10-09 07:00:00.000' AS DateTime), CAST(2900 AS Decimal(18, 0)), CAST(450 AS Decimal(18, 0)), CAST(N'2019-10-09 14:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (16, 1, CAST(N'2020-01-03 07:00:00.000' AS DateTime), CAST(345 AS Decimal(18, 0)), CAST(567 AS Decimal(18, 0)), CAST(N'2020-01-03 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (17, 1, CAST(N'2020-01-04 07:00:00.000' AS DateTime), CAST(547 AS Decimal(18, 0)), CAST(234 AS Decimal(18, 0)), CAST(N'2020-01-04 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (18, 1, CAST(N'2020-01-05 07:00:00.000' AS DateTime), CAST(447 AS Decimal(18, 0)), CAST(434 AS Decimal(18, 0)), CAST(N'2020-01-05 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (19, 1, CAST(N'2020-01-02 07:00:00.000' AS DateTime), CAST(876 AS Decimal(18, 0)), CAST(1110 AS Decimal(18, 0)), CAST(N'2020-01-02 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (20, 2, CAST(N'2020-01-05 07:00:00.000' AS DateTime), CAST(511 AS Decimal(18, 0)), CAST(1300 AS Decimal(18, 0)), CAST(N'2020-01-05 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (21, 2, CAST(N'2020-01-06 07:00:00.000' AS DateTime), CAST(3900 AS Decimal(18, 0)), CAST(455 AS Decimal(18, 0)), CAST(N'2020-01-06 18:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (22, 2, CAST(N'2019-12-17 07:00:00.000' AS DateTime), CAST(456 AS Decimal(18, 0)), CAST(321 AS Decimal(18, 0)), CAST(N'2019-12-17 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (23, 2, CAST(N'2020-01-06 07:00:00.000' AS DateTime), CAST(491 AS Decimal(18, 0)), CAST(125 AS Decimal(18, 0)), CAST(N'2020-01-06 18:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (24, 2, CAST(N'2020-01-07 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-07 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (25, 2, CAST(N'2020-01-08 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-08 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (26, 2, CAST(N'2020-01-09 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-09 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (27, 3, CAST(N'2020-01-10 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-10 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (28, 3, CAST(N'2020-01-11 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-11 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (29, 3, CAST(N'2020-01-12 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-12 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (30, 3, CAST(N'2020-01-13 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-13 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (31, 3, CAST(N'2020-01-14 07:00:00.000' AS DateTime), CAST(500 AS Decimal(18, 0)), CAST(500 AS Decimal(18, 0)), CAST(N'2020-01-14 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (32, 3, CAST(N'2020-01-14 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(100 AS Decimal(18, 0)), CAST(N'2020-01-14 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (33, 3, CAST(N'2020-01-15 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(100 AS Decimal(18, 0)), CAST(N'2020-01-15 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (34, 3, CAST(N'2020-01-16 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(100 AS Decimal(18, 0)), CAST(N'2020-01-16 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (35, 3, CAST(N'2020-01-17 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(700 AS Decimal(18, 0)), CAST(N'2020-01-17 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (36, 3, CAST(N'2020-01-18 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(750 AS Decimal(18, 0)), CAST(N'2020-01-18 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (37, 3, CAST(N'2020-01-19 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(750 AS Decimal(18, 0)), CAST(N'2020-01-19 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (38, 3, CAST(N'2020-01-20 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(750 AS Decimal(18, 0)), CAST(N'2020-01-20 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (39, 3, CAST(N'2020-01-21 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(250 AS Decimal(18, 0)), CAST(N'2020-01-21 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (40, 3, CAST(N'2020-01-22 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(250 AS Decimal(18, 0)), CAST(N'2020-01-22 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (41, 3, CAST(N'2020-01-23 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(250 AS Decimal(18, 0)), CAST(N'2020-01-23 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (42, 3, CAST(N'2020-01-24 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-24 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (43, 3, CAST(N'2020-01-25 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-25 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (44, 3, CAST(N'2020-01-26 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-26 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (45, 3, CAST(N'2020-01-27 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-27 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (46, 3, CAST(N'2020-01-28 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-28 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (47, 3, CAST(N'2020-01-29 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(850 AS Decimal(18, 0)), CAST(N'2020-01-29 17:00:00.000' AS DateTime))
INSERT [dbo].[report] ([id], [task_id], [departure], [weight], [distance], [arrival]) VALUES (48, 3, CAST(N'2020-01-31 07:00:00.000' AS DateTime), CAST(100 AS Decimal(18, 0)), CAST(399 AS Decimal(18, 0)), CAST(N'2020-01-31 17:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[role] ON

INSERT [dbo].[role] ([id], [name]) VALUES (1, N'ADMIN')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'DRIVER')
SET IDENTITY_INSERT [dbo].[role] OFF
SET IDENTITY_INSERT [dbo].[task] ON

INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward]) VALUES (1, 7500, 511, 1, 4, 1, 2000)
INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward]) VALUES (3, 3790.9, 1200.1, 2, 5, 1, 1150)
INSERT [dbo].[task] ([id], [summary_distance], [weight], [driver_id], [car_id], [status_id], [reward]) VALUES (4, 2310.9, 800.1, 5, 6, 1, 780)
SET IDENTITY_INSERT [dbo].[task] OFF
INSERT [dbo].[task_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[task_status] ([id], [name]) VALUES (2, N'IN PROCESS')
INSERT [dbo].[task_status] ([id], [name]) VALUES (3, N'VALIDATING')
INSERT [dbo].[task_status] ([id], [name]) VALUES (4, N'REJECTED')
INSERT [dbo].[task_status] ([id], [name]) VALUES (5, N'FINISHED')
SET IDENTITY_INSERT [dbo].[user] ON

INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (1, N'Ivanov', N'Ivan', N'Ivanovich', CAST(N'1989-01-13 00:00:00.000' AS DateTime), N'ivanov', N'ivanov', 0, 2, 1)
INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (2, N'Sidorov', N'Igor', N'Igorevich', CAST(N'1973-02-03 00:00:00.000' AS DateTime), N'sidorov', N'sidorov', 0, 2, 1)
INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (5, N'Novikov', N'Maxim', N'Nikolaevich', CAST(N'1991-12-27 00:00:00.000' AS DateTime), N'novikov', N'novikov', 0, 2, 1)
INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (7, N'Ivanov', N'Afanasiy', N'Konstantinovich', CAST(N'1978-06-05 00:00:00.000' AS DateTime), N'ivanov', N'ivanov', 0, 2, 1)
INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (9, N'Vasilev', N'Arkadiy', N'Arkkadievich', CAST(N'1923-04-04 00:00:00.000' AS DateTime), N'vasiliev', N'vasiliev', 0, 2, 1)
INSERT [dbo].[user] ([id], [lastName], [firstName], [patronymic], [birthDay], [login], [password], [money], [roleId], [statusId]) VALUES (13, N'Admin', N'Admin', N'Admin', CAST(N'1990-04-04 00:00:00.000' AS DateTime), N'admin', N'admin', 1000000, 1, 1)
SET IDENTITY_INSERT [dbo].[user] OFF
INSERT [dbo].[user_status] ([id], [name]) VALUES (1, N'FREE')
INSERT [dbo].[user_status] ([id], [name]) VALUES (2, N'BUSY')
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_brand] FOREIGN KEY([brandId])
REFERENCES [dbo].[brand] ([id])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [FK_car_brand]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [FK_car_car_status] FOREIGN KEY([statusId])
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
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_role] FOREIGN KEY([roleId])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_role]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_user_status] FOREIGN KEY([statusId])
REFERENCES [dbo].[user_status] ([id])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_user_status]
GO
