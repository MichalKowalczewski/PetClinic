/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2014 (12.0.2000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2017
    Target Database Engine Edition : Microsoft SQL Server Standard Edition
    Target Database Engine Type : Standalone SQL Server
*/
CREATE DATABASE [PetClinic]
GO
/****** Object:  Table [dbo].[Owner]    Script Date: 2017-10-05 09:18:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Owner](
	[OwnerID] [int] IDENTITY(1,1) NOT NULL,
	[OwnerFirstName] [nvarchar](50) NOT NULL,
	[OwnerLastName] [nvarchar](50) NOT NULL,
	[Address] [varchar](50) NULL,
	[City] [varchar](50) NULL,
	[Phone] [int] NULL,
 CONSTRAINT [PK_Owner] PRIMARY KEY CLUSTERED 
(
	[OwnerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pet]    Script Date: 2017-10-05 09:18:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pet](
	[PetID] [int] IDENTITY(1,1) NOT NULL,
	[PetName] [varchar](50) NOT NULL,
	[PetTypeId] [int] NOT NULL,
	[PetOwnerID] [int] NOT NULL,
 CONSTRAINT [PK_Pet] PRIMARY KEY CLUSTERED 
(
	[PetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 2017-10-05 09:18:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type](
	[TypeID] [int] IDENTITY(1,1) NOT NULL,
	[TypeName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Type] PRIMARY KEY CLUSTERED 
(
	[TypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vet]    Script Date: 2017-10-05 09:18:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vet](
	[VetID] [int] IDENTITY(1,1) NOT NULL,
	[VetFirstName] [varchar](50) NOT NULL,
	[VetLastName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Vet] PRIMARY KEY CLUSTERED 
(
	[VetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Visit]    Script Date: 2017-10-05 09:18:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Visit](
	[VisitID] [int] IDENTITY(1,1) NOT NULL,
	[VisitPetID] [int] NOT NULL,
	[VisitVetID] [int] NOT NULL,
	[VisitDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Visit] PRIMARY KEY CLUSTERED 
(
	[VisitID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Pet]  WITH CHECK ADD  CONSTRAINT [FK_Pet_Owner] FOREIGN KEY([PetOwnerID])
REFERENCES [dbo].[Owner] ([OwnerID])
GO
ALTER TABLE [dbo].[Pet] CHECK CONSTRAINT [FK_Pet_Owner]
GO
ALTER TABLE [dbo].[Pet]  WITH CHECK ADD  CONSTRAINT [FK_Pet_Type] FOREIGN KEY([PetTypeId])
REFERENCES [dbo].[Type] ([TypeID])
GO
ALTER TABLE [dbo].[Pet] CHECK CONSTRAINT [FK_Pet_Type]
GO
ALTER TABLE [dbo].[Visit]  WITH CHECK ADD  CONSTRAINT [FK_Visit_Pet] FOREIGN KEY([VisitPetID])
REFERENCES [dbo].[Pet] ([PetID])
GO
ALTER TABLE [dbo].[Visit] CHECK CONSTRAINT [FK_Visit_Pet]
GO
ALTER TABLE [dbo].[Visit]  WITH CHECK ADD  CONSTRAINT [FK_Visit_Vet] FOREIGN KEY([VisitVetID])
REFERENCES [dbo].[Vet] ([VetID])
GO
ALTER TABLE [dbo].[Visit] CHECK CONSTRAINT [FK_Visit_Vet]
GO
