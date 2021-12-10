-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2021 at 03:20 PM
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
(3, 'Kids');

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
(12, 'Purple Fog', '#8679A2');

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
(8, 2, 3, 'Nike Blazer Mid \'77 Essential', 1429000, 'VINTAGE HOOPS STYLE.\r\n\r\n\r\nGo ahead, perfect your outfit.The Nike Blazer Mid \'77 delivers a timeless design that\'s easy to wear.Its crisp leather in the upper breaks in beautifully and pairs with bold retro branding and luscious accents for a premium feel.\r\n\r\n\r\nBenefits\r\n\r\n- Leather and synthetic upper looks crisp and is easy to style.\r\n- Large retro Swoosh features a metallic finish with star pattern texturing.\r\n- Autoclave construction fuses the outsole to the midsole for a streamlined look that\'s timeless.\r\n- Exposed foam on the tongue provides a throwback look.\r\n- Non-marking rubber outsole with herringbone pattern for excellent traction and durability.\r\n\r\nProduct Details\r\n\r\n- Mid-height, padded collar\r\n- Foam insole\r\n- Traditional lacing\r\n- Country/Region of Origin: Vietnam\r\n\r\nBlazer Origins\r\n\r\nOriginally introduced in 1972 as a basketball shoe, the Blazer has since transformed into a modern staple for skaters and sneakerheads alike.Maturing from a simple canvas high top to a leather mid top and casual low top, this shoe just gets better with age.');

-- --------------------------------------------------------

--
-- Table structure for table `product_details`
--

CREATE TABLE `product_details` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rating` double NOT NULL,
  `style` varchar(50) NOT NULL,
  `color_description` varchar(30) NOT NULL,
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
(1, 1, 4.6, 'DJ3624-003', 'Particle Grey/Armoury Navy/Lig', 1, 1283, 0, 'ms0001PG01.png', 'ms0001PG02.png', NULL, NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(2, 1, 4.5, 'DJ3624-001', 'Black/Metallic Silver/Total Or', 2, 1384, 0, 'ms0002BL01.png', 'ms0002BL02.png', NULL, NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(3, 1, 4.4, 'DJ3624-100', 'White/Light Bone/Black/Game Ro', 3, 987, 0, 'ms0003WH01.png', 'ms0003WH02.png', NULL, NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(4, 1, 4.6, 'DJ3624-400', 'Team Royal/Red Clay/Light Bone', 4, 1484, 0, 'ms0004WH01.png', 'ms0004WH02.png', NULL, NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(5, 2, 4.8, 'DC9134-002', 'Sea Glass/Metallic Gold/Clear ', 5, 1983, 0, 'ms0002SG01.png', 'ms0002SG02.png', 'ms0002SG03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(6, 2, 4.7, 'DC9134-001', 'Black/White/Multi-Colour', 2, 1532, 0, 'ms0002BL01.png', 'ms0002BL02.png', 'ms0002BL03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(7, 2, 4.6, 'DC9134-400', 'Aluminium/Black/Sail', 6, 948, 0, 'ms0002AL01.png', 'ms0002AL02.png', 'ms0002AL03.png', NULL, NULL, '2021-12-07 07:31:44', '2021-12-07 07:31:44'),
(12, 4, 4.8, 'DA0694-600', 'Crimson Bliss', 7, 928, 0, 'ms0004CB01.png', 'ms0004CB02.png', NULL, NULL, NULL, '2021-12-09 13:19:17', '2021-12-09 13:19:17'),
(13, 4, 4.8, 'DA0694-101', 'White/Clear Emerald/Total Oran', 3, 1382, 0, 'ms0004WH01.png', 'ms0004WH02.png', 'ms0004WH03.png', NULL, NULL, '2021-12-09 13:21:19', '2021-12-09 13:21:19'),
(14, 5, 4.3, 'DN9909-100', 'Summit White/Oil Green/Rattan/', 8, 183, 0, 'ws0001SW01.png', 'ws0001SW02.png', 'ws0001SW03.png', NULL, NULL, '2021-12-09 14:00:26', '2021-12-09 14:00:26'),
(15, 5, 4.2, 'DN9909-200', 'Pearl White/Rattan/Pink Oxford', 9, 948, 0, 'ws0001PW01.png', 'ws0001PW02.png', 'ws0001PW03.png', NULL, NULL, '2021-12-09 14:05:13', '2021-12-09 14:05:13'),
(16, 6, 4.5, 'DD1525-001', 'Black/Metallic Silver/Flat Sil', 2, 3982, 0, 'ws0002BL01.png', 'ws0002BL02.png', 'ws0002BL03.png', NULL, NULL, '2021-12-09 14:09:07', '2021-12-09 14:09:07'),
(17, 6, 4.6, 'DD1525-100', 'White/Metallic Gold/Flat Gold/', 3, 918, 0, 'ws0002WH01.png', 'ws0002WH02.png', 'ws0002WH03.png', NULL, NULL, '2021-12-09 14:09:07', '2021-12-09 14:09:07');

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
-- Indexes for table `types`
--
ALTER TABLE `types`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `colors`
--
ALTER TABLE `colors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product_details`
--
ALTER TABLE `product_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `types`
--
ALTER TABLE `types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
