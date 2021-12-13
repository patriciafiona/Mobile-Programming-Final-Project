-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2021 at 11:42 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nike`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Men'),
(2, 'Women'),
(3, 'Boys Kids'),
(4, 'Girls Kids');

-- --------------------------------------------------------

--
-- Table structure for table `colors`
--

CREATE TABLE `colors` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `color_code` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `colors`
--

INSERT INTO `colors` (`id`, `name`, `color_code`) VALUES
(1, 'Particle Grey', '#6A7A7F'),
(2, 'Black', '#343031'),
(3, 'White', '#F2F2F4'),
(4, 'Team Royal', '#023E6B'),
(5, 'Sea Glass', '#E6E6E6'),
(6, 'Aluminium', '#8CA5C0'),
(7, 'Crimson Bliss', '#F5A199'),
(8, 'Summit White', '#E5E7E4'),
(9, 'Pearl White', '#D1CFC9'),
(10, 'Violet Blue', '#66A0AA'),
(11, 'Haiti', '#26252A'),
(12, 'Purple Fog', '#8679A2'),
(13, 'Resolution Blue', '#233D7C'),
(14, 'Pink Foam', '#F2E2E6');

-- --------------------------------------------------------

--
-- Table structure for table `con_test`
--

CREATE TABLE `con_test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `category_id`, `type_id`, `name`, `price`, `description`) VALUES
(1, 1, 1, 'Nike Air Max Dawn', 1649000, 'RETRO DELIGHT.\r\n\r\n\r\nRooted to athletics DNA, the Air Max Dawn is made from at least 20% recycled material by weight.Synthetic suede and other materials blend vintage running vibes with fresh details.Nike Air cushioning delivers a feel-good forecast for the day.\r\n\r\n\r\nBenefits\r\n\r\n- Design lines echo the heritage look you love, while the last brings you a top-down appearance.\r\n- Low-profile Air-sole unit with retro pill-shaped window is paired with a plush foam midsole for cushioning.\r\n- Outsole with see-saw pattern adds traction and durability\r\n\r\nProduct Details\r\n\r\n- Pull tab on heel\r\n- Heel clip\r\n- Country/Region of Origin: Indonesia\r\n\r\nNike Air Max Origins\r\n\r\nRevolutionary Air technology first made its way into Nike footwear in 1978.In 1987, the Air Max 1 debuted with visible Air technology in its heel, allowing fans more than just the feel of Air cushioning—suddenly they could see it.Since then, next-generation Air Max shoes have become a hit with athletes and collectors by offering striking colour combinations and reliable, lightweight cushioning.'),
(2, 1, 2, 'Kyrie Infinity EP', 1379000, 'ATTACK WITH CONTROL.\r\n\r\n\r\nThe faster Kyrie slows down, the quicker he can speed up or change direction. His ability to control his movement keeps defenders guessing—and him in control. The Kyrie Infinity EP provides a cushioned, custom fit and traction up the sides, allowing players to accelerate and decelerate on demand and take advantage of the separation they create.\r\n\r\n\r\nCustom Feel\r\n\r\nKyrie likes his shoes to feel tight and custom fitted like a boxing glove. Internal bands poke through the upper, and the laces feed through them. Tightening the laces pulls down on the bands to create a super-snug, comfortable and connected fit.\r\n\r\n\r\nCourt-Ready Cushioning\r\n\r\nAn updated 3-layer cushioning system features responsive Air Zoom Strobel in the forefoot sandwiched between a moulded sockliner and foam midsole. Less foam and fewer layers improve court feel and unlock the Air Zoom\'s energy-returning potential.\r\n\r\n\r\nCut with Control\r\n\r\nMulti-directional tread is designed around how Kyrie cuts and jabs on the court, providing traction precisely where it\'s needed. The grippy rubber wraps up the sides, especially on the inside heel and toe, so you stay in control when banking and changing directions.\r\n\r\n\r\nMore Benefits\r\n\r\n- Translucent, micro-engineered upper material is lightweight and breathable.\r\n- Plush, padded collar is partially separated around the heel for snug support around the Achilles tendon.\r\n- Extra-durable rubber outsole is designed to play on outdoor courts.\r\n- Country/Region of Origin: China'),
(4, 1, 2, 'Zoom Freak 3', 1860000, 'THE THIRD.\r\n\r\n\r\nGiannis is an athlete of freakish power and incredible range. His ability to play any position make him difficult to guard and nearly impossible to stop. The Zoom Freak 3 helps Giannis create space with his massive strides and misdirecting Euro-step. The molded midfoot strap and external overlay provide side-to-side stability when he\'s powering to the rim, while the multidirectional traction helps him stay in control.\r\n\r\n\r\nDriving Force\r\n\r\nThe lightweight, molded midsole is hollowed out under the forefoot, where 2 Zoom Air units are positioned. This allows the Zoom units to compress under the weight then expand and help return energy.\r\n\r\n\r\nTop-Down Control\r\n\r\nA molded hook-and-loop strap is made from flexible, durable TPU. It wraps over the lower laces to help reduce internal foot movement by harnessing you over the cushioning.\r\n\r\n\r\nEuro-Step Stability\r\n\r\nAn external piece of lightweight, durable TPU is built into the outer-side midsole. It helps keep your foot stable over the footbed when making quick side-to-side directional changes.\r\n\r\n\r\nTraction to Run\r\n\r\nThe computer-generated outsole has a pattern like a topographical map. It helps optimize ground contact for traction in multiple directions.\r\n\r\n\r\nMore Benefits\r\n\r\n- Cutouts in the foam under the toe and heel help reduce overall weight.\r\n- Molded grooves in the rubber outsole help the shoe flex.\r\n\r\nProduct Details\r\n\r\n- Tongue pull tab\r\n- Reverse Swoosh design\r\n- Reinforced toe\r\n- Standard lacing'),
(5, 2, 1, 'Nike Dunk High LX', 1979000, 'BRINGING OLD-SCHOOL STYLE TO WINTER.\r\n\r\n\r\nA shoe walks into a sleeping bag, stumbles into a puffy jacket and then ends up with a hot chocolate by the fireside.It\'s kinda like that.Only better.Plus, this pair is made from with at least 20% recycled content by weight.\r\n\r\n\r\nBenefits\r\n\r\n- Stitched overlays keep the heritage look while quilted details pair perfectly with your favourite puffy jacket.\r\n- Warm, fleece-like lining feels soft and extends over the collar for a quintessential wintertime look.\r\n- Rubber outsole is infused with cork and - Nike Grind, giving you a classic wintertime look that is easy to style.Nike Grind material is made from scraps from the footwear manufacturing process.\r\n- Foam midsole offers lightweight, responsive cushioning.\r\n- Country/Region of Origin: Indonesia\r\n\r\nDunk Origins\r\n\r\nOriginally a classic Nike hoops shoe, the Dunk was organically adopted by skate culture—and in time re-engineered for Nike SB.These days, the SB Dunk doubles as a starting point for many of the brand\'s most influential design collaborators, from small-town skate shops to iconic New York fashion houses.'),
(6, 2, 1, 'Nike Air Force 1 \'07 LX', 1649000, 'LEGEND WITH A CHAIN.\r\n\r\n\r\nThe radiance lives on in the Nike Air Force 1 \'07 LX, the b-ball classic that puts a fresh spin on what you know best: stitched overlays, bold details and the perfect amount of hoops style to make heads turn.The removable gold chain with pendants lets you customise your look with flair.\r\n\r\n\r\nBenefits\r\n\r\n- Stitched leather overlays on the upper are smoother than backboard glass and add heritage style.\r\n- Removable gold chain with pendants lets you customise your look.\r\n- Originally designed for performance hoops, Nike Air cushioning adds comfort.\r\n- Padded, low-cut collar looks sleek and feels great.\r\n- Full-length rubber outsole with classic hoops pivot circles and star design at the toe adds traction and durability.\r\n\r\nProduct Details\r\n\r\n- Foam midsole\r\n- Perforations on the toe\r\n\r\nAir Force 1 Origins\r\n\r\nDebuting in 1982, the AF-1 was the first basketball shoe to house Nike Air, revolutionising the game while rapidly gaining traction around the world.Today, the Air Force 1 stays true to its roots with the same soft and springy cushioning that changed sneaker history.\r\n\r\n'),
(7, 2, 2, 'Nike Dunk High By You', 1979000, 'THE POWER OF DUNK, REDEFINED.\r\n\r\nRock it like it\'s hot and bring some of that \'00s magic back to the future in the Nike Dunk High By You.Leather, suede and woven material in rave colours and metallics bring a whole new life to this staple of sport, with a special edition font to add a little extra personality.\r\n\r\nPop Palette Picks\r\n\r\nCurate the materials for your retro-forward masterpiece with genuine and synthetic leather, suede and woven material with a bit of shine.Then mix and match neons, metallics, varsity hues and natural tones for a maximalist look that\'s all your own.\r\n\r\nRad Personalisation Picks\r\n\r\nPut your message (up to 8 characters) on the heel in bold colours.\r\n\r\nIconic Comfort\r\n\r\nOriginally designed for performance basketball, this super-comfortable shoe has been loved and adopted by everyone, from skaters to stars.Put your spin on the icon that everyone wants to get their hands on.\r\n\r\nMore Benefits\r\n\r\n- The classic foam midsole offers lightweight, responsive cushioning for comfort that lasts all day long.\r\n- A padded, high-top collar adds more space for your creativity.\r\n- The rubber outsole has a classic hoops pivot circle and adds durability, traction and heritage style.'),
(8, 2, 3, 'Nike Blazer Mid \'77 Essential', 1429000, 'VINTAGE HOOPS STYLE.\r\n\r\n\r\nGo ahead, perfect your outfit.The Nike Blazer Mid \'77 delivers a timeless design that\'s easy to wear.Its crisp leather in the upper breaks in beautifully and pairs with bold retro branding and luscious accents for a premium feel.\r\n\r\n\r\nBenefits\r\n\r\n- Leather and synthetic upper looks crisp and is easy to style.\r\n- Large retro Swoosh features a metallic finish with star pattern texturing.\r\n- Autoclave construction fuses the outsole to the midsole for a streamlined look that\'s timeless.\r\n- Exposed foam on the tongue provides a throwback look.\r\n- Non-marking rubber outsole with herringbone pattern for excellent traction and durability.\r\n\r\nProduct Details\r\n\r\n- Mid-height, padded collar\r\n- Foam insole\r\n- Traditional lacing\r\n- Country/Region of Origin: Vietnam\r\n\r\nBlazer Origins\r\n\r\nOriginally introduced in 1972 as a basketball shoe, the Blazer has since transformed into a modern staple for skaters and sneakerheads alike.Maturing from a simple canvas high top to a leather mid top and casual low top, this shoe just gets better with age.'),
(9, 3, 1, 'Nike Air Force 1 LV8', 1199000, 'CLASSIC HOOPS MEETS RACING CULTURE.\r\n\r\n\r\nVrooom!The Nike Air Force 1 LV8 goes super-fast with graphics and details inspired by motor bikes.Combined with classic leather and the iconic Air-Sole unit, this \'82 legend gets turned up to another level and speed.Come along for the ride!\r\n\r\n\r\nSpeedy Style\r\n\r\nBlurry Nike graphics and the grid print on the tongue give a fast, motor-bike-like feel.\r\n\r\n\r\nThrowback Vibes\r\n\r\nReal and synthetic leather combine for durability and classic AF-1 style.\r\n\r\n\r\nAll About Air\r\n\r\nThe hidden Air-Sole unit provides cushioning just like the original.\r\n\r\n\r\nProduct Details\r\n\r\n- Classic laces\r\n- Rubber sole\r\n- Not intended for use as Personal Protective Equipment (PPE)\r\n- Country/Region of Origin: Indonesia'),
(10, 3, 2, 'Kyrie Infinity', 1469000, 'MADE FOR QUICK MOVES WITH KYRIE\'S SWAG.\r\n\r\n\r\nPlay with the sizzle and flair like one of the game\'s best in the Kyrie Infinity. This edition of Kyrie Irving\'s signature shoe is made for flexibility and a snug fit so you can cross over and change directions quickly on the court. His signature logo on the tongue displays the stamp of the champ while you play.\r\n\r\n\r\nPlay Fast\r\n\r\nThe shape and feel of the soft foam underfoot is optimised for the flexibility you need to accelerate and bounce around the court.\r\n\r\n\r\nSecure Fit\r\n\r\nThe laces work with a built-in system that works with the sides and the heel of the shoe for a super-snug fit around your ankle.\r\n\r\n\r\nBuilt to Breathe\r\n\r\nTextile is made to feel lightweight and breathable so you can stay cool while playing. The see-through design lets you watch the secure lacing system at work.\r\n\r\n\r\nGround Control\r\n\r\nThe pattern on the rubber sole is data driven so you can feel confident moving in all directions and angles—just like Kyrie. The rubber wraps up on the sides of the shoe for traction while cutting and stopping on a dime.\r\n\r\n\r\nProduct Details\r\n\r\n- Regular laces\r\n- Country/Region of Origin: China'),
(11, 3, 1, 'Nike Air Zoom Crossover', 979000, 'CHANGING THE FUTURE OF THE GAME.\r\n\r\n\r\nOur first basketball shoe inspired by ladies of the league is made for ALL young hoopers to dominate their game.The Nike Air Zoom Crossover, named after a favourite move, brings an extra bounce to your step with Zoom Air while you fly sky high on the court.\r\n\r\n\r\nBy Her, For All\r\n\r\nThe rubber outsole pattern is inspired by tracking of weather patterns—meant to simulate the storm of the growing youth and women\'s game.Graphics and design details tie back to Nike\'s Swoosh Fly collection—our signature apparel line for women\'s basketball.\r\n\r\n\r\nAll About Air\r\n\r\nA Zoom Air unit in the heel provides responsive cushioning for an extra-bouncy feel.\r\n\r\n\r\nCool Moves\r\n\r\nLightweight mesh provides breathability so your feet stay cool and comfortable on the court.\r\n\r\n\r\nMore Benefits\r\n\r\n- The toe area is designed to give plenty of room for growing feet.\r\n- The collar fits snug around the ankle for support.\r\n- Grooves in the sole provide flexibility so each step feels natural.\r\n\r\nProduct Details\r\n\r\n- Classic laces\r\n- Country/Region of Origin: Vietnam'),
(12, 3, 3, 'Nike Blazer Mid \'77 SE D', 1219000, 'BUST A MOVE WITH A THROWBACK HOOPS SHOE.\r\n\r\n\r\nThis Blazer is designed to inspire dance moves for the kid on the go.Start 2-stepping in soft suede and a mid-top style that will help you feel fun and fearless, wherever the beat takes you.Ready to step up?\r\n\r\n\r\nStrut Your Stuff\r\n\r\nThis special edition is inspired by everything dance.The collar is designed with extra padding for a plusher feel while you move.Design lines are re-imagined to mimic the fluid movement of dance.\r\n\r\n\r\nBuilt to Last\r\n\r\nThe rubber overlay on the toe tip is made longer for extra durability for toe draggers and dancing on the tips of your feet.Plus, a rubber sole provides traction.\r\n\r\n\r\nThrowback Vibes\r\n\r\nLeather provides durability, support and a classic feel like the original Blazer.\r\n\r\n\r\nProduct Details\r\n\r\n- Classic laces\r\n- Soft linings inside the shoe\r\n- Country/Region of Origin: Indonesia'),
(13, 4, 1, 'Nike Revolution 6', 799000, 'COMFORT FOR EVERY STRIDE.\r\n\r\n\r\nWe prioritise comfort, especially for our growing athletes. Made with 20% recycled content by weight, you can take these lightweight, breathable running shoes from all-day play to any-time wear and even athletics training. Lightweight, breathable and cushioned for growing feet, the race starts now.\r\n\r\n\r\nBenefits\r\n\r\n- Lightweight textile provides breathability so you can stay cool.\r\n- Heel overlay offers support and stability for multi-directional movements.\r\n- Soft foam underfoot creates plush cushioning with every stride.\r\n- Rubber sole gives durable traction.\r\n- Extra skin layer across the toes provides reinforcement against toe dragging.\r\n\r\nProduct Details\r\n\r\n- Classic laces\r\n- Country/Region of Origin: Vietnam,Indonesia'),
(14, 4, 2, 'Nike Team Hustle D 10', 799000, 'MADE FOR ALL BALLERS.\r\n\r\n\r\nFrom playtime to getting buckets, the Nike Team Hustle D 10 is a slam dunk for players of all levels. With optimised traction on the sole, more secure lacing, plus a design that\'s durable and supportive, these are a major score whether you\'re shooting hoops or climbing on the playground.\r\n\r\n\r\nGround Control\r\n\r\nUsing pressure-mapping, the rubber sole is designed for grip during quick cuts and moves in all directions. A curved heel and flexibility grooves help make every step feel natural.\r\n\r\n\r\nLaced and Ready\r\n\r\nLacing combines with strategically placed webbing so one pull on your laces helps make the entire fit feel snug.\r\n\r\n\r\nSupportive Feel\r\n\r\nMesh provides breathability to keep your feet cool. Leather around the heel brings stability and support while you run and jump.\r\n\r\n\r\nMore Benefits\r\n\r\n- Soft foam underfoot and around the heel provides lightweight cushioning.\r\n- Reinforced toe tip adds durability when kids drag their toes.\r\n- Asymmetrical collar design makes it easy for ankle flexibility.\r\n- The \"X\" on the bottom is a nod to the 10th edition of the Team Hustle D series.\r\n- Country/Region of Origin: Indonesia'),
(15, 4, 2, 'LeBron 19', 1219000, 'LIGHT LIKE AIR WITH LEBRON STYLE.\r\n\r\n\r\nFuture champs can rule playtime like King James in the LeBron 19.This next evolution in LeBron\'s signature shoe is lightweight and cushioned just for kids.Plus, we added in supportive design where little ones need it so they can run and walk around with the swag of LeBron.\r\n\r\n\r\nBenefits\r\n\r\n- Textile hugs little feet for a lightweight and durable design.\r\n- Soft foam underfoot provides plush cushioning.\r\n- A midfoot plastic clip brings support and stability.\r\n- A rubber sole provides durable traction.\r\n- Classic lacing has a classic and secure feel.\r\n- Country/Region of Origin: Indonesia'),
(16, 4, 1, 'Nike Air Force 1 Crater', 1219000, 'A LEGEND REDEFINED.\r\n\r\n\r\nThere\'s a fun secret about the Nike Air Force 1 Crater: It\'s got sustainable materials from top to bottom.A see-through design provides a peek into the legend of these classic kicks.Plus, super-soft Crater foam is made kid-right and lets you give a little back with every step.These shoes contain at least 20% recycled content by weight.\r\n\r\n\r\nRevolutionary Comfort\r\n\r\nCrater foam cushioning feels super-soft underfoot.Mixed with Nike Grind material (made from grinding up old footwear) and other Nike foams, it feels lightweight and bouncy.It\'s also designed to be extra durable and flexible for kids.\r\n\r\n\r\nBuilt to Last\r\n\r\nSynthetic leather, synthetic suede and textile combine for durability and support to bring a new feel to the \'82 legend.\r\n\r\n\r\nAll About Air\r\n\r\nA hidden Air-Sole unit under the heel provides cushioning just like the original AF-1.\r\n\r\n\r\nMore Benefits\r\n\r\nThe transparent design around the heel lets you peek into the inner workings of the shoe.\r\n\r\nProduct Details\r\n\r\n- Classic laces\r\n- Country/Region of Origin: Vietnam'),
(17, 1, 3, 'Nike Blazer Mid \'77 Cozi By You', 1979000, 'YOUR OWN WARM STYLE.\r\n\r\nPut your own spin on the Nike Blazer Mid \'77, a wardrobe staple ready for winter. Make a one-of-a-kind, hoops-inspired icon that\'s yours and yours alone by putting your own touch on the upper, sole and other details. Warm, insulated fill in key areas adds classic cold-weather appeal.\r\n\r\nKEEP THEM WARM\r\n\r\nInsulated fill helps keep your feet warm on cold days. Go with solid, a pop of colour or graphic—it\'s up to you.\r\n\r\nYOUR OWN TOUCH\r\n\r\nPiece together your own upper using leather, suede or synthetic leather for different parts of the shoe.Add a word or phrase to the tongue label—up to 30 characters, 10 per line.\r\n\r\nCLASSIC AND CUSTOM\r\n\r\nVulcanised construction fuses the outsole to the midsole for a streamlined look you can wear anywhere.Choose what kind of rubber you want to use for the outsole.\r\n\r\nPRODUCT DETAILS\r\n\r\n- Mid-height, padded collar\r\n- Rubber outsole with herringbone pattern\r\n- Option for reflective design details\r\n- Not intended for use as Personal Protective Equipment (PPE)');

-- --------------------------------------------------------

--
-- Table structure for table `product_details`
--

CREATE TABLE `product_details` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rating` double NOT NULL,
  `style` varchar(50) NOT NULL,
  `color_description` varchar(70) NOT NULL,
  `color_id` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `discount` int(11) NOT NULL DEFAULT 0,
  `photo_01` varchar(256) NOT NULL,
  `photo_02` varchar(256) DEFAULT NULL,
  `photo_03` varchar(256) DEFAULT NULL,
  `photo_04` varchar(256) DEFAULT NULL,
  `photo_05` varchar(256) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_details`
--

INSERT INTO `product_details` (`id`, `product_id`, `rating`, `style`, `color_description`, `color_id`, `stock`, `discount`, `photo_01`, `photo_02`, `photo_03`, `photo_04`, `photo_05`, `created_at`, `updated_at`) VALUES
(1, 1, 4.6, 'DJ3624-003', 'Particle Grey/Armoury Navy/Light Bone/Dark Citron', 1, 1283, 0, 'ms0001PG01.png', 'ms0001PG02.png', 'ms0001PG03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(2, 1, 4.5, 'DJ3624-001', 'Black/Metallic Silver/Total Orange/Summit White', 2, 1384, 0, 'ms0001BL01.png', 'ms0001BL02.png', 'ms0001BL03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(3, 1, 4.4, 'DJ3624-100', 'White/Light Bone/Black/Game Royal', 3, 987, 0, 'ms0001WH01.png', 'ms0001WH02.png', 'ms0001WH03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(4, 1, 4.6, 'DJ3624-400', 'Team Royal/Red Clay/Light Bone/Summit White', 4, 1484, 0, 'ms0001TR01.png', 'ms0001TR02.png', 'ms0001TR03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(5, 2, 4.8, 'DC9134-002', 'Sea Glass/Metallic Gold/Clear Emerald/Dynamic Turquoise', 5, 1983, 0, 'ms0002SG01.png', 'ms0002SG02.png', 'ms0002SG03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(6, 2, 4.7, 'DC9134-001', 'Black/White/Multi-Colour', 2, 1532, 0, 'ms0002BL01.png', 'ms0002BL02.png', 'ms0002BL03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(7, 2, 4.6, 'DC9134-400', 'Aluminium/Black/Sail', 6, 948, 0, 'ms0002AL01.png', 'ms0002AL02.png', 'ms0002AL03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(12, 4, 4.8, 'DA0694-600', 'Crimson Bliss', 7, 928, 0, 'ms0004CB01.png', 'ms0004CB02.png', NULL, NULL, NULL, '2021-12-09 13:19:17', '2021-12-09 13:19:17'),
(13, 4, 4.8, 'DA0694-101', 'White/Clear Emerald/Total Orange/Black', 3, 1382, 0, 'ms0004WH01.png', 'ms0004WH02.png', 'ms0004WH03.png', NULL, NULL, '2021-12-09 13:21:19', '2021-12-09 13:21:19'),
(14, 5, 4.3, 'DN9909-100', 'Summit White/Oil Green/Rattan/Black', 8, 183, 0, 'ws0001SW01.png', 'ws0001SW02.png', 'ws0001SW03.png', NULL, NULL, '2021-12-09 14:00:26', '2021-12-09 14:00:26'),
(15, 5, 4.2, 'DN9909-200', 'Pearl White/Rattan/Pink Oxford', 9, 948, 0, 'ws0001PW01.png', 'ws0001PW02.png', 'ws0001PW03.png', NULL, NULL, '2021-12-09 14:05:13', '2021-12-09 14:05:13'),
(16, 6, 4.5, 'DD1525-001', 'Black/Metallic Silver/Flat Silver/Black', 2, 3982, 0, 'ws0002BL01.png', 'ws0002BL02.png', 'ws0002BL03.png', NULL, NULL, '2021-12-09 14:09:07', '2021-12-09 14:09:07'),
(17, 6, 4.6, 'DD1525-100', 'White/Metallic Gold/Flat Gold/White', 3, 918, 0, 'ws0002WH01.png', 'ws0002WH02.png', 'ws0002WH03.png', NULL, NULL, '2021-12-09 14:09:07', '2021-12-09 14:09:07'),
(18, 7, 4.9, 'DJ7030-991', 'Multi-Colour/Multi-Colour/Multi-Colour/Multi-Colour', 10, 19, 0, 'ws0003VB01.png', 'ws0003VB02.png', 'ws0003VB03.png', NULL, NULL, '2021-12-09 14:26:05', '2021-12-09 14:26:05'),
(19, 7, 4.9, 'DJ7030-991', 'Multi-Colour/Multi-Colour/Multi-Colour/Multi-Colour', 9, 23, 0, 'ws0003PW01.png', 'ws0003PW02.png', 'ws0003PW03.png', NULL, NULL, '2021-12-09 14:26:05', '2021-12-09 14:26:05'),
(20, 7, 4.9, 'DJ7030-991', 'Multi-Colour/Multi-Colour/Multi-Colour/Multi-Colour', 11, 19, 0, 'ws0003HT01.png', 'ws0003HT02.png', 'ws0003HT03.png', NULL, NULL, '2021-12-09 14:26:40', '2021-12-09 14:26:40'),
(21, 7, 4.8, 'DJ7030-991', 'Multi-Colour/Multi-Colour/Multi-Colour/Multi-Colour', 13, 28, 0, 'ws0003RB01.png', 'ws0003RB02.png', 'ws0003RB03.png', NULL, NULL, '2021-12-09 14:26:40', '2021-12-09 14:26:40'),
(22, 8, 4.6, 'DH0070-100', 'White/Black/Metallic Silver', 3, 1393, 0, 'ws0004WH01.png', 'ws0004WH02.png', 'ws0004WH03.png', NULL, NULL, '2021-12-09 14:45:57', '2021-12-09 14:45:57'),
(23, 8, 4.7, 'DH0070-001', 'Black/White/Metallic Gold', 2, 2893, 0, 'ws0004BL01.png', 'ws0004BL02.png', 'ws0004BL03.png', NULL, NULL, '2021-12-09 14:45:57', '2021-12-09 14:45:57'),
(24, 9, 4.6, 'DO3809-100', 'White/Black/Game Royal/White', 3, 948, 20, 'bks0001WH01.png', 'bks0001WH02.png', 'bks0001WH03.png', NULL, NULL, '2021-12-10 03:09:24', '2021-12-10 03:09:24'),
(25, 10, 4.7, 'DD0334-400', 'Aluminium/Black/Bright Crimson/Sail', 6, 1532, 0, 'bks0002AL01.png', 'bks0002AL02.png', 'bks0002AL03.png', NULL, NULL, '2021-12-10 03:09:24', '2021-12-10 03:09:24'),
(26, 10, 4.8, 'DD0334-001', 'Black/White/Team Orange/Multi-Colour', 2, 1484, 0, 'bks0002BL01.png', 'bks0002BL02.png', 'bks0002BL03.png', NULL, NULL, '2021-12-10 03:11:09', '2021-12-10 03:11:09'),
(27, 11, 4.7, 'DC5216-101', 'White/Copa/Black', 3, 1283, 0, 'bks0003WH01.png', 'bks0003WH02.png', 'bks0003WH03.png', NULL, NULL, '2021-12-10 03:14:50', '2021-12-10 03:14:50'),
(28, 11, 4.7, 'DC5216-001', 'Black/Dark Smoke Grey/Photon Dust/Chrome', 2, 973, 0, 'bks0003BL01.png', 'bks0003BL02.png', 'bks0003BL03.png', NULL, NULL, '2021-12-10 03:14:50', '2021-12-10 03:14:50'),
(29, 12, 4.4, 'DH8640-100', 'White/University Red/Light Smoke Grey/Black', 3, 1943, 0, 'bks0004WH01.png', 'bks0004WH02.png', 'bks0004WH03.png', NULL, NULL, '2021-12-10 03:16:56', '2021-12-10 03:16:56'),
(30, 13, 4.6, 'DD1096-608', 'Pink Foam/Black', 14, 1139, 0, 'gks0001PF01.png', 'gks0001PF02.png', 'gks0001PF03.png', NULL, NULL, '2021-12-10 03:25:58', '2021-12-10 03:25:58'),
(31, 13, 4.7, 'DD1096-002', 'Black/White/Metallic Gold', 2, 920, 0, 'gks0001BL01.png', 'gks0001BL02.png', 'gks0001BL03.png', NULL, NULL, '2021-12-10 03:25:58', '2021-12-10 03:25:58'),
(32, 14, 4.8, 'CW6735-002', 'Black/White/Photon Dust/Metallic Gold', 2, 1789, 0, 'gks0002BL01.png', 'gks0002BL02.png', 'gks0002BL03.png', NULL, NULL, '2021-12-10 03:32:28', '2021-12-10 03:32:28'),
(33, 15, 4.7, 'DD0421-100', 'White/Blue Void/Dutch Blue', 3, 983, 0, 'gks0003WH01.png', 'gks0003WH02.png', 'gks0003WH03.png', NULL, NULL, '2021-12-10 03:32:28', '2021-12-10 03:32:28'),
(34, 16, 4.3, 'DH4339-100', 'White/Orange/White', 3, 981, 19, 'gks0004WH01.png', 'gks0004WH02.png', 'gks0004WH03.png', NULL, NULL, '2021-12-10 03:36:21', '2021-12-10 03:36:21'),
(35, 17, 4.9, 'DN4115-991', 'Black/White/Gray', 2, 39, 0, 'ms0005BL01.png', 'ms0005BL02.png', 'ms0005BL03.png', NULL, NULL, '2021-12-10 15:48:44', '2021-12-10 15:48:44');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `color_id` int(11) NOT NULL,
  `discount` double NOT NULL,
  `price` float NOT NULL,
  `size` int(11) NOT NULL,
  `isPaid` tinyint(1) NOT NULL,
  `pay_method` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

CREATE TABLE `types` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`id`, `name`) VALUES
(1, 'Sneakers'),
(2, 'Basketball'),
(3, 'High Tops');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `birthday` date NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `type_id` (`type_id`);

--
-- Indexes for table `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`,`color_id`),
  ADD KEY `color_id` (`color_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`,`product_id`,`color_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `color_id` (`color_id`);

--
-- Indexes for table `types`
--
ALTER TABLE `types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `colors`
--
ALTER TABLE `colors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `product_details`
--
ALTER TABLE `product_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `types`
--
ALTER TABLE `types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `product_details_ibfk_1` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `product_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
