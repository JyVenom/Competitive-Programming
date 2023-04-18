//import java.io.*;
//import java.util.StringTokenizer;
//
//public class BOMB2 {
//    public static void main(String[] args) throws IOException {
//        InputReader2 ir = new InputReader2();
//        PrintWriter pw = new PrintWriter(System.out);
//
//        String[] wires = new String[20];
//        for (int i = 0; i < 20; i++) {
//            wires[i] = ir.nextLine();
//        }
//
//        boolean good = false;
//        String data = """
//                If the 17th wire is silver, cut the 10th wire.
//                If the 20th wire is wine, cut the 20th wire.
//                If there are exactly 18 gold wires, cut the 8th wire.
//                If the 12th wire is pearl, cut the 11th wire.
//                If there are exactly 14 caramel wires, cut the 11th wire.
//                If there are more than 11 oyster wires, cut the 16th wire.
//                If there are exactly 12 seaweed wires, cut the 5th wire.
//                If there are more than 18 emerald wires, cut the 4th wire.
//                If there are more than 14 fire wires, cut the 1st wire.
//                If there are exactly 18 lapis wires, cut the 3rd wire.
//                If the 11th wire is berry, cut the 2nd wire.
//                If there is exactly 1 pineapple wire, cut the 12th wire.
//                If there is exactly 1 blood wire, cut the 15th wire.
//                If there are exactly 4 green wires, cut the 16th wire.
//                If the 20th wire is fern, cut the 6th wire.
//                If the 8th wire is berry, cut the 14th wire.
//                If there are exactly 12 navy wires, cut the 5th wire.
//                If the 4th wire is peach, cut the 9th wire.
//                If there are more than 7 apricot wires, cut the 17th wire.
//                If the 7th wire is obsidian, cut the 16th wire.
//                If there are exactly 13 ruby wires, cut the 15th wire.
//                If the 15th wire is black, cut the 8th wire.
//                If there are exactly 17 grape wires, cut the 17th wire.
//                If there are exactly 2 granola wires, cut the 16th wire.
//                If there are exactly 15 macaroon wires, cut the 7th wire.
//                If there are more than 8 iris wires, cut the 7th wire.
//                If the 13th wire is cobalt, cut the 1st wire.
//                If there are exactly 8 hickory wires, cut the 12th wire.
//                If the 11th wire is wine, cut the 3rd wire.
//                If the 8th wire is charcoal, cut the 10th wire.
//                If the 13th wire is silver, cut the 10th wire.
//                If the 16th wire is dijon, cut the 19th wire.
//                If there are more than 9 caramel wires, cut the 4th wire.
//                If there are exactly 9 squash wires, cut the 5th wire.
//                If the 16th wire is tortilla, cut the 7th wire.
//                If the 4th wire is white, cut the 18th wire.
//                If there are exactly 16 hickory wires, cut the 2nd wire.
//                If there are exactly 5 coconut wires, cut the 2nd wire.
//                If there are more than 19 fawn wires, cut the 13th wire.
//                If there are exactly 14 white wires, cut the 12th wire.
//                If the 10th wire is buttermilk, cut the 1st wire.
//                If there are exactly 17 orange wires, cut the 12th wire.
//                If there are exactly 19 gray wires, cut the 7th wire.
//                If there are exactly 5 banana wires, cut the 16th wire.
//                If the 10th wire is marigold, cut the 9th wire.
//                If there are exactly 12 red wires, cut the 14th wire.
//                If the 4th wire is ivory, cut the 12th wire.
//                If there are exactly 15 carrot wires, cut the 5th wire.
//                If there are exactly 11 bronze wires, cut the 6th wire.
//                If there are exactly 9 corn wires, cut the 4th wire.
//                If there are exactly 13 marigold wires, cut the 17th wire.
//                If there are exactly 12 white wires, cut the 17th wire.
//                If there are more than 7 carrot wires, cut the 18th wire.
//                If the 10th wire is seaweed, cut the 15th wire.
//                If the 16th wire is pickle, cut the 16th wire.
//                If the 16th wire is green, cut the 4th wire.
//                If there are exactly 13 rust wires, cut the 18th wire.
//                If there are exactly 15 porcelain wires, cut the 3rd wire.
//                If there are exactly 2 cobalt wires, cut the 4th wire.
//                If there are exactly 2 blood wires, cut the 16th wire.
//                If there are exactly 18 sandstone wires, cut the 8th wire.
//                If there are more than 8 candy wires, cut the 2nd wire.
//                If there are more than 9 violet wires, cut the 11th wire.
//                If the 16th wire is green, cut the 14th wire.
//                If there are exactly 17 arctic wires, cut the 1st wire.
//                If there are exactly 18 wood wires, cut the 1st wire.
//                If there are exactly 12 fern wires, cut the 8th wire.
//                If the 10th wire is orchid, cut the 14th wire.
//                If there are exactly 11 dijon wires, cut the 14th wire.
//                If there are exactly 19 rose wires, cut the 13th wire.
//                If there are exactly 12 crimson wires, cut the 10th wire.
//                If there are exactly 6 stone wires, cut the 13th wire.
//                If the 20th wire is green, cut the 20th wire.
//                If there are exactly 6 rose wires, cut the 15th wire.
//                If the 16th wire is cloud, cut the 5th wire.
//                If there are exactly 5 gold wires, cut the 19th wire.
//                If the 5th wire is fawn, cut the 11th wire.
//                If there are more than 12 green wires, cut the 14th wire.
//                If there are exactly 12 squash wires, cut the 13th wire.
//                If the 15th wire is rouge, cut the 12th wire.
//                If the 8th wire is watermelon, cut the 18th wire.
//                If the 20th wire is tan, cut the 5th wire.
//                If there are exactly 17 orchid wires, cut the 9th wire.
//                If the 17th wire is dijon, cut the 3rd wire.
//                If there are exactly 20 rouge wires, cut the 13th wire.
//                If there are more than 14 rouge wires, cut the 9th wire.
//                If there are exactly 12 teal wires, cut the 19th wire.
//                If there are exactly 15 red wires, cut the 15th wire.
//                If the 13th wire is sand, cut the 3rd wire.
//                If the 11th wire is obsidian, cut the 15th wire.
//                If there are exactly 10 macaroon wires, cut the 20th wire.
//                If the 3rd wire is gray, cut the 4th wire.
//                If the 14th wire is bone, cut the 7th wire.
//                If there are exactly 9 marmalade wires, cut the 13th wire.
//                If there are more than 15 navy wires, cut the 7th wire.
//                If there are exactly 16 apricot wires, cut the 5th wire.
//                If there are exactly 14 scarlet wires, cut the 11th wire.
//                If the 8th wire is fawn, cut the 5th wire.
//                If there are exactly 20 gray wires, cut the 14th wire.
//                If there are more than 7 apricot wires, cut the 8th wire.
//                If there are exactly 10 arctic wires, cut the 8th wire.
//                If there are exactly 4 silver wires, cut the 2nd wire.
//                If the 7th wire is basil, cut the 13th wire.
//                If there are exactly 7 silver wires, cut the 5th wire.
//                If the 1st wire is dijon, cut the 20th wire.
//                If the 4th wire is pineapple, cut the 14th wire.
//                If the 10th wire is ruby, cut the 1st wire.
//                If there are exactly 19 pearl wires, cut the 9th wire.
//                If the 8th wire is fern, cut the 17th wire.
//                If there are exactly 7 bronze wires, cut the 2nd wire.
//                If the 6th wire is rose, cut the 19th wire.
//                If there are exactly 18 rose wires, cut the 1st wire.
//                If there is exactly 1 basil wire, cut the 6th wire.
//                If there are exactly 8 white wires, cut the 5th wire.
//                If there are exactly 11 crepe wires, cut the 3rd wire.
//                If there are exactly 4 ash wires, cut the 7th wire.
//                If the 3rd wire is orchid, cut the 18th wire.
//                If there are exactly 5 bubblegum wires, cut the 15th wire.
//                If the 2nd wire is shortbread, cut the 5th wire.
//                If there are exactly 6 gold wires, cut the 20th wire.
//                If there are exactly 19 hickory wires, cut the 10th wire.
//                If the 15th wire is yellow, cut the 12th wire.
//                If there are exactly 13 iris wires, cut the 4th wire.
//                If the 10th wire is daisy, cut the 10th wire.
//                If the 4th wire is chiffon, cut the 20th wire.
//                If there are more than 13 hazelnut wires, cut the 3rd wire.
//                If there are exactly 16 macaroon wires, cut the 6th wire.
//                If there are more than 14 ivory wires, cut the 6th wire.
//                If the 11th wire is cotton, cut the 13th wire.
//                If there are exactly 9 squash wires, cut the 20th wire.
//                If the 10th wire is cherry, cut the 18th wire.
//                If there are exactly 2 sandstone wires, cut the 16th wire.
//                If the 5th wire is arctic, cut the 5th wire.
//                If there are more than 14 violet wires, cut the 12th wire.
//                If there are more than 14 orchid wires, cut the 11th wire.
//                If there are exactly 8 peach wires, cut the 4th wire.
//                If the 10th wire is navy, cut the 16th wire.
//                If there are exactly 13 arctic wires, cut the 20th wire.
//                If there are more than 19 dijon wires, cut the 11th wire.
//                If there is exactly 1 coral wire, cut the 11th wire.
//                If there are exactly 16 marigold wires, cut the 2nd wire.
//                If there are exactly 18 lapis wires, cut the 10th wire.
//                If there are more than 16 rouge wires, cut the 10th wire.
//                If there are exactly 2 peach wires, cut the 15th wire.
//                If there are exactly 6 gold wires, cut the 5th wire.
//                If there are exactly 9 azure wires, cut the 12th wire.
//                If there are exactly 18 azure wires, cut the 15th wire.
//                If there are exactly 16 butter wires, cut the 14th wire.
//                If there are exactly 17 oyster wires, cut the 13th wire.
//                If there are more than 16 gold wires, cut the 14th wire.
//                If there are exactly 5 coffee wires, cut the 13th wire.
//                If there are exactly 4 eggplant wires, cut the 12th wire.
//                If the 2nd wire is grape, cut the 17th wire.
//                If there are exactly 5 crimson wires, cut the 15th wire.
//                If there are exactly 18 tan wires, cut the 12th wire.
//                If there are exactly 8 bubblegum wires, cut the 11th wire.
//                If there are exactly 13 rose wires, cut the 9th wire.
//                If there are exactly 5 white wires, cut the 16th wire.
//                If the 5th wire is watermelon, cut the 20th wire.
//                If there are exactly 9 violet wires, cut the 7th wire.
//                If the 17th wire is pink, cut the 7th wire.
//                If there are more than 10 lapis wires, cut the 3rd wire.
//                If there are exactly 19 watermelon wires, cut the 7th wire.
//                If the 14th wire is green, cut the 11th wire.
//                If the 5th wire is blush, cut the 18th wire.
//                If there are exactly 4 sandstone wires, cut the 12th wire.
//                If the 8th wire is charcoal, cut the 16th wire.
//                If the 13th wire is crepe, cut the 3rd wire.
//                If there are exactly 18 bronze wires, cut the 10th wire.
//                If there are exactly 16 fawn wires, cut the 5th wire.
//                If there are exactly 12 candy wires, cut the 15th wire.
//                If there are exactly 11 jade wires, cut the 13th wire.
//                If there are exactly 13 chiffon wires, cut the 20th wire.
//                If there are exactly 7 sage wires, cut the 6th wire.
//                If the 18th wire is green, cut the 1st wire.
//                If the 10th wire is charcoal, cut the 4th wire.
//                If the 9th wire is honey, cut the 9th wire.
//                If there are exactly 12 blood wires, cut the 12th wire.
//                If the 1st wire is banana, cut the 8th wire.
//                If there are exactly 5 teal wires, cut the 7th wire.
//                If there are exactly 10 orange wires, cut the 15th wire.
//                If there are exactly 17 blush wires, cut the 5th wire.
//                If there are exactly 9 lapis wires, cut the 15th wire.
//                If there are more than 19 pear wires, cut the 10th wire.
//                If the 7th wire is pear, cut the 20th wire.
//                If the 18th wire is blush, cut the 2nd wire.
//                If the 16th wire is chiffon, cut the 19th wire.
//                If the 17th wire is orchid, cut the 4th wire.
//                If there are exactly 17 pink wires, cut the 17th wire.
//                If the 6th wire is blood, cut the 9th wire.
//                If the 19th wire is grape, cut the 19th wire.
//                If the 9th wire is cherry, cut the 18th wire.
//                If there are exactly 4 basil wires, cut the 4th wire.
//                If there are more than 10 violet wires, cut the 15th wire.
//                If there are exactly 14 rosewood wires, cut the 4th wire.
//                If there are exactly 4 arctic wires, cut the 11th wire.
//                If the 8th wire is honey, cut the 18th wire.
//                If there are exactly 12 flint wires, cut the 14th wire.
//                If there are exactly 3 violet wires, cut the 13th wire.
//                If there are exactly 2 jade wires, cut the 5th wire.
//                If there are more than 15 shamrock wires, cut the 5th wire.
//                If the 7th wire is cotton, cut the 14th wire.
//                If there are exactly 2 rosewood wires, cut the 4th wire.
//                If there are exactly 2 blush wires, cut the 17th wire.
//                If the 17th wire is hazelnut, cut the 13th wire.
//                If the 3rd wire is rust, cut the 15th wire.
//                If there are exactly 5 candy wires, cut the 7th wire.
//                If there are exactly 15 teal wires, cut the 19th wire.
//                If there are exactly 5 iris wires, cut the 4th wire.
//                If the 14th wire is brown, cut the 13th wire.
//                If the 17th wire is crepe, cut the 15th wire.
//                If there are exactly 20 coffee wires, cut the 9th wire.
//                If the 11th wire is candy, cut the 15th wire.
//                If the 7th wire is scarlet, cut the 9th wire.
//                If there are exactly 8 coffee wires, cut the 17th wire.
//                If there are exactly 19 hickory wires, cut the 4th wire.
//                If there are exactly 18 cloud wires, cut the 17th wire.
//                If there are more than 17 chocolate wires, cut the 4th wire.
//                If there are exactly 5 bronze wires, cut the 13th wire.
//                If the 4th wire is teal, cut the 1st wire.
//                If there are exactly 20 iris wires, cut the 13th wire.
//                If the 8th wire is ash, cut the 10th wire.
//                If there are more than 12 sand wires, cut the 4th wire.
//                If the 3rd wire is chiffon, cut the 15th wire.
//                If there are exactly 8 red wires, cut the 18th wire.
//                If the 18th wire is carrot, cut the 7th wire.
//                If the 18th wire is stone, cut the 10th wire.
//                If the 2nd wire is pink, cut the 19th wire.
//                If there are exactly 15 tiger wires, cut the 7th wire.
//                If there are more than 16 lime wires, cut the 17th wire.
//                If there are exactly 20 caramel wires, cut the 19th wire.
//                If there are exactly 18 violet wires, cut the 4th wire.
//                If the 1st wire is sage, cut the 16th wire.
//                If there are more than 8 corn wires, cut the 5th wire.
//                If the 2nd wire is penny, cut the 19th wire.
//                If there are exactly 4 teal wires, cut the 10th wire.
//                If the 2nd wire is iris, cut the 18th wire.
//                If the 8th wire is iris, cut the 5th wire.
//                If there are more than 18 sage wires, cut the 1st wire.
//                If the 12th wire is red, cut the 11th wire.
//                If the 19th wire is tiger, cut the 8th wire.
//                If there are exactly 13 sage wires, cut the 19th wire.
//                If the 6th wire is lime, cut the 4th wire.
//                If there are exactly 8 flint wires, cut the 20th wire.
//                If there are exactly 18 ruby wires, cut the 20th wire.
//                If the 19th wire is corn, cut the 12th wire.
//                If there are more than 12 honey wires, cut the 12th wire.
//                If there are exactly 6 watermelon wires, cut the 16th wire.
//                If the 5th wire is marmalade, cut the 8th wire.
//                If there are exactly 18 porcelain wires, cut the 13th wire.
//                If there are exactly 8 pineapple wires, cut the 19th wire.
//                If there are exactly 17 tiger wires, cut the 10th wire.
//                If there is exactly 1 azure wire, cut the 6th wire.
//                If there are exactly 16 teal wires, cut the 17th wire.
//                If the 7th wire is sky, cut the 20th wire.
//                If there are exactly 20 hazelnut wires, cut the 16th wire.
//                If there are exactly 7 magenta wires, cut the 12th wire.
//                If there are more than 13 crimson wires, cut the 20th wire.
//                If the 17th wire is green, cut the 3rd wire.
//                If there are exactly 11 rosewood wires, cut the 1st wire.
//                If the 10th wire is corn, cut the 15th wire.
//                If the 19th wire is dijon, cut the 14th wire.
//                If there are exactly 5 gray wires, cut the 10th wire.
//                If there are exactly 16 macaroon wires, cut the 16th wire.
//                If there are exactly 19 bronze wires, cut the 8th wire.
//                If the 9th wire is pink, cut the 13th wire.
//                If there are exactly 10 marmalade wires, cut the 1st wire.
//                If the 8th wire is hazelnut, cut the 4th wire.
//                If the 19th wire is bronze, cut the 13th wire.
//                If there are exactly 2 coconut wires, cut the 10th wire.
//                If the 8th wire is sage, cut the 7th wire.
//                If there are exactly 16 buttermilk wires, cut the 7th wire.
//                If the 14th wire is chiffon, cut the 19th wire.
//                If the 10th wire is brown, cut the 16th wire.
//                If there are exactly 6 rosewood wires, cut the 8th wire.
//                If there are more than 17 pear wires, cut the 9th wire.
//                If there are more than 10 magenta wires, cut the 8th wire.
//                If there are exactly 12 peach wires, cut the 15th wire.
//                If there are exactly 2 teal wires, cut the 13th wire.
//                If the 19th wire is buttermilk, cut the 13th wire.
//                If the 1st wire is honey, cut the 3rd wire.
//                If there are exactly 8 granola wires, cut the 19th wire.
//                If there are exactly 20 teal wires, cut the 6th wire.
//                If the 15th wire is white, cut the 14th wire.
//                If there are more than 16 granola wires, cut the 13th wire.
//                If the 11th wire is pink, cut the 2nd wire.
//                If there are more than 16 candy wires, cut the 15th wire.
//                If the 19th wire is apricot, cut the 14th wire.
//                If there are exactly 19 candy wires, cut the 20th wire.
//                If there are more than 12 watermelon wires, cut the 1st wire.
//                If there are exactly 5 honey wires, cut the 11th wire.
//                If there are exactly 16 shortbread wires, cut the 8th wire.
//                If there are more than 13 pickle wires, cut the 1st wire.
//                If there are exactly 9 plum wires, cut the 2nd wire.
//                If the 1st wire is iris, cut the 8th wire.
//                If there are exactly 16 coconut wires, cut the 10th wire.
//                If there are exactly 17 green wires, cut the 19th wire.
//                If the 11th wire is dijon, cut the 13th wire.
//                If there are exactly 15 grape wires, cut the 15th wire.
//                If the 11th wire is orchid, cut the 1st wire.
//                If there are more than 7 rouge wires, cut the 3rd wire.
//                If there is exactly 1 seaweed wire, cut the 13th wire.
//                If there are exactly 16 cotton wires, cut the 16th wire.
//                If the 16th wire is shortbread, cut the 3rd wire.
//                If there are exactly 19 black wires, cut the 3rd wire.
//                If there are exactly 7 marigold wires, cut the 9th wire.
//                If the 16th wire is granola, cut the 4th wire.
//                If there are exactly 19 scarlet wires, cut the 12th wire.
//                If the 5th wire is berry, cut the 9th wire.
//                If there are exactly 5 sandstone wires, cut the 1st wire.
//                If the 5th wire is pearl, cut the 16th wire.
//                If there are exactly 4 candy wires, cut the 10th wire.
//                If the 2nd wire is coral, cut the 8th wire.
//                If the 2nd wire is basil, cut the 5th wire.
//                If the 1st wire is pearl, cut the 5th wire.
//                If there are exactly 14 arctic wires, cut the 3rd wire.
//                If the 15th wire is azure, cut the 15th wire.
//                If there are exactly 7 teal wires, cut the 4th wire.
//                If the 8th wire is honey, cut the 7th wire.
//                If there are more than 7 hickory wires, cut the 11th wire.
//                If there is exactly 1 bone wire, cut the 13th wire.
//                If there are exactly 16 scarlet wires, cut the 17th wire.
//                If there are exactly 16 iron wires, cut the 4th wire.
//                If there are exactly 18 pearl wires, cut the 2nd wire.
//                If the 20th wire is lead, cut the 18th wire.
//                If the 20th wire is scarlet, cut the 12th wire.
//                If there are exactly 19 caramel wires, cut the 16th wire.
//                If there are exactly 13 macaroon wires, cut the 8th wire.
//                If there are exactly 2 jade wires, cut the 16th wire.
//                If there are exactly 9 lead wires, cut the 17th wire.
//                If there are exactly 18 macaroon wires, cut the 18th wire.
//                If there are exactly 2 jade wires, cut the 19th wire.
//                If the 16th wire is banana, cut the 7th wire.
//                If there are exactly 10 cherry wires, cut the 12th wire.
//                If there are exactly 14 tiger wires, cut the 4th wire.
//                If there are exactly 16 sky wires, cut the 20th wire.
//                If the 8th wire is marigold, cut the 4th wire.
//                If there are exactly 15 rouge wires, cut the 19th wire.
//                If there are exactly 6 banana wires, cut the 20th wire.
//                If there are exactly 20 pear wires, cut the 14th wire.
//                If there are exactly 8 magenta wires, cut the 13th wire.
//                If the 16th wire is ivory, cut the 5th wire.
//                If the 5th wire is iron, cut the 8th wire.
//                If there are exactly 6 pearl wires, cut the 6th wire.
//                If there are exactly 18 lime wires, cut the 20th wire.
//                If the 13th wire is fawn, cut the 16th wire.
//                If there are exactly 11 charcoal wires, cut the 15th wire.
//                If the 13th wire is hazelnut, cut the 16th wire.
//                If there are exactly 7 porcelain wires, cut the 1st wire.
//                If there are more than 15 purple wires, cut the 11th wire.
//                If the 10th wire is brown, cut the 9th wire.
//                If there are exactly 5 sage wires, cut the 8th wire.
//                If the 2nd wire is jade, cut the 17th wire.
//                If there is exactly 1 tan wire, cut the 2nd wire.
//                If the 2nd wire is jade, cut the 5th wire.
//                If there are exactly 8 rose wires, cut the 5th wire.
//                If the 18th wire is lead, cut the 7th wire.
//                If there are exactly 2 charcoal wires, cut the 5th wire.
//                If the 11th wire is marmalade, cut the 5th wire.
//                If the 14th wire is crepe, cut the 9th wire.
//                If the 7th wire is sky, cut the 5th wire.
//                If the 19th wire is candy, cut the 9th wire.
//                If there is exactly 1 grape wire, cut the 11th wire.
//                If there are more than 16 lapis wires, cut the 2nd wire.
//                If there are exactly 14 crimson wires, cut the 16th wire.
//                If the 8th wire is yellow, cut the 11th wire.
//                If there are exactly 5 purple wires, cut the 11th wire.
//                If there are exactly 16 tiger wires, cut the 11th wire.
//                If there are exactly 6 tan wires, cut the 19th wire.
//                If there are more than 15 crimson wires, cut the 18th wire.
//                If there are exactly 18 wood wires, cut the 6th wire.
//                If the 8th wire is brown, cut the 20th wire.
//                If there are exactly 12 penny wires, cut the 2nd wire.
//                If the 1st wire is grape, cut the 20th wire.
//                If the 5th wire is silver, cut the 19th wire.
//                If the 4th wire is caramel, cut the 11th wire.
//                If there are exactly 2 stone wires, cut the 15th wire.
//                If there are exactly 7 chocolate wires, cut the 20th wire.
//                If the 18th wire is blush, cut the 6th wire.
//                If the 8th wire is teal, cut the 3rd wire.
//                If there are exactly 15 cobalt wires, cut the 5th wire.
//                If the 3rd wire is ruby, cut the 15th wire.
//                If there are exactly 8 gold wires, cut the 18th wire.
//                If there are exactly 15 orchid wires, cut the 15th wire.
//                If there is exactly 1 butter wire, cut the 2nd wire.
//                If there are exactly 17 rouge wires, cut the 12th wire.
//                If the 20th wire is peach, cut the 20th wire.
//                If the 10th wire is coconut, cut the 5th wire.
//                If the 20th wire is emerald, cut the 17th wire.
//                If there are more than 7 plum wires, cut the 19th wire.
//                If the 4th wire is penny, cut the 11th wire.
//                If there is exactly 1 charcoal wire, cut the 19th wire.
//                If there are exactly 6 coconut wires, cut the 14th wire.
//                If there are exactly 10 sand wires, cut the 9th wire.
//                If there are exactly 3 hazelnut wires, cut the 18th wire.
//                If there are exactly 20 marmalade wires, cut the 18th wire.
//                If there are more than 7 basil wires, cut the 13th wire.
//                If there are exactly 11 pink wires, cut the 2nd wire.
//                If there are exactly 10 honey wires, cut the 7th wire.
//                If the 12th wire is blood, cut the 19th wire.
//                If there are exactly 19 amethyst wires, cut the 17th wire.
//                If there are exactly 4 amethyst wires, cut the 16th wire.
//                If there is less than 1 red wire, cut the 4th wire.
//                If there are exactly 7 fern wires, cut the 7th wire.
//                If there are more than 15 charcoal wires, cut the 1st wire.
//                If there are exactly 2 cobalt wires, cut the 18th wire.
//                If there are exactly 12 honey wires, cut the 17th wire.
//                If there are exactly 5 azure wires, cut the 15th wire.
//                If the 5th wire is shamrock, cut the 5th wire.
//                If there are exactly 17 bone wires, cut the 12th wire.
//                If there are exactly 9 gray wires, cut the 14th wire.
//                If there are exactly 13 candy wires, cut the 20th wire.
//                If the 3rd wire is cloud, cut the 6th wire.
//                If there are exactly 10 cotton wires, cut the 12th wire.
//                If there are exactly 15 rouge wires, cut the 12th wire.
//                If there are exactly 5 rust wires, cut the 7th wire.
//                If the 15th wire is lead, cut the 10th wire.
//                If there are more than 7 banana wires, cut the 2nd wire.
//                If there are exactly 19 ash wires, cut the 16th wire.
//                If there is exactly 1 magenta wire, cut the 20th wire.
//                If there are exactly 6 fern wires, cut the 7th wire.
//                If there are exactly 14 tiger wires, cut the 20th wire.
//                If there are exactly 12 berry wires, cut the 9th wire.
//                If there are more than 15 jade wires, cut the 5th wire.
//                If there are exactly 15 pear wires, cut the 10th wire.
//                If the 6th wire is pear, cut the 8th wire.
//                If the 17th wire is violet, cut the 15th wire.
//                If the 2nd wire is pickle, cut the 11th wire.
//                If there are exactly 15 obsidian wires, cut the 2nd wire.
//                If the 11th wire is plum, cut the 10th wire.
//                If the 1st wire is penny, cut the 6th wire.
//                If the 9th wire is magenta, cut the 2nd wire.
//                If there are exactly 9 granola wires, cut the 12th wire.
//                If the 19th wire is carrot, cut the 8th wire.
//                If the 9th wire is crepe, cut the 7th wire.
//                If the 7th wire is iron, cut the 6th wire.
//                If the 15th wire is iron, cut the 19th wire.
//                If there are exactly 18 wine wires, cut the 4th wire.
//                If the 7th wire is chocolate, cut the 3rd wire.
//                If there are exactly 5 peach wires, cut the 8th wire.
//                If there are exactly 8 sand wires, cut the 16th wire.
//                If there are exactly 20 wood wires, cut the 20th wire.
//                If the 10th wire is tortilla, cut the 10th wire.
//                If the 4th wire is ivory, cut the 2nd wire.
//                If the 7th wire is gray, cut the 13th wire.
//                If the 15th wire is buttermilk, cut the 18th wire.
//                If there are exactly 11 orchid wires, cut the 1st wire.
//                If there are exactly 5 shamrock wires, cut the 5th wire.
//                If the 12th wire is frost, cut the 18th wire.
//                If there are exactly 12 orange wires, cut the 10th wire.
//                If there are exactly 15 purple wires, cut the 12th wire.
//                If there are exactly 8 fern wires, cut the 20th wire.
//                If the 15th wire is white, cut the 12th wire.
//                If there are exactly 15 bone wires, cut the 15th wire.
//                If the 10th wire is apricot, cut the 19th wire.
//                If there are exactly 7 stone wires, cut the 4th wire.
//                If the 15th wire is shamrock, cut the 12th wire.
//                If there are exactly 12 crepe wires, cut the 18th wire.
//                If there are exactly 15 red wires, cut the 1st wire.
//                If there are exactly 13 sky wires, cut the 7th wire.
//                If there are exactly 9 pineapple wires, cut the 12th wire.
//                If there are exactly 17 cherry wires, cut the 2nd wire.
//                If there are more than 14 brown wires, cut the 16th wire.
//                If the 1st wire is peach, cut the 2nd wire.
//                If there are exactly 13 coconut wires, cut the 19th wire.
//                If there is exactly 1 carrot wire, cut the 20th wire.
//                If the 5th wire is blood, cut the 18th wire.
//                If there are exactly 4 charcoal wires, cut the 12th wire.
//                If there are exactly 19 blood wires, cut the 11th wire.
//                If the 19th wire is blue, cut the 15th wire.
//                If there are exactly 18 granola wires, cut the 15th wire.
//                If there are exactly 19 pickle wires, cut the 1st wire.
//                If there are exactly 13 plum wires, cut the 3rd wire.
//                If there are exactly 9 granola wires, cut the 6th wire.
//                If there are exactly 19 black wires, cut the 19th wire.
//                If there are exactly 14 coconut wires, cut the 18th wire.
//                If there are exactly 12 ruby wires, cut the 7th wire.
//                If there are exactly 20 azure wires, cut the 7th wire.
//                If there are exactly 15 sage wires, cut the 3rd wire.
//                If the 12th wire is pickle, cut the 1st wire.
//                If there are exactly 4 lead wires, cut the 19th wire.
//                If there are exactly 17 azure wires, cut the 7th wire.
//                If the 10th wire is sky, cut the 7th wire.
//                If there are exactly 9 teal wires, cut the 9th wire.
//                If there are exactly 8 pickle wires, cut the 9th wire.
//                If there are exactly 7 rosewood wires, cut the 15th wire.
//                If there are exactly 6 shortbread wires, cut the 8th wire.
//                If the 18th wire is brown, cut the 16th wire.
//                If there are exactly 13 seaweed wires, cut the 16th wire.
//                If there are exactly 15 bronze wires, cut the 12th wire.
//                If the 7th wire is bubblegum, cut the 20th wire.
//                If there are exactly 13 butter wires, cut the 15th wire.
//                If there are exactly 2 oyster wires, cut the 14th wire.
//                If there are more than 19 shamrock wires, cut the 12th wire.
//                If the 11th wire is apricot, cut the 7th wire.
//                If the 19th wire is pearl, cut the 10th wire.
//                If the 8th wire is iris, cut the 6th wire.
//                If the 17th wire is shortbread, cut the 2nd wire.
//                If the 8th wire is lapis, cut the 14th wire.
//                If there are exactly 19 emerald wires, cut the 18th wire.
//                If the 2nd wire is sandstone, cut the 14th wire.
//                If there are exactly 18 rosewood wires, cut the 6th wire.
//                If the 12th wire is berry, cut the 13th wire.
//                If the 14th wire is pear, cut the 2nd wire.
//                If the 10th wire is corn, cut the 4th wire.
//                If the 11th wire is pickle, cut the 3rd wire.
//                If there are exactly 9 navy wires, cut the 10th wire.
//                If there are exactly 2 chiffon wires, cut the 8th wire.
//                If the 16th wire is hazelnut, cut the 8th wire.
//                If there are exactly 19 penny wires, cut the 12th wire.
//                If the 11th wire is obsidian, cut the 15th wire.
//                If there are exactly 19 red wires, cut the 7th wire.
//                If there is exactly 1 cherry wire, cut the 16th wire.
//                If there are exactly 8 bone wires, cut the 20th wire.
//                If the 6th wire is watermelon, cut the 9th wire.
//                If the 10th wire is cobalt, cut the 9th wire.
//                If there are exactly 5 flint wires, cut the 4th wire.
//                If the 9th wire is coconut, cut the 2nd wire.
//                If the 7th wire is iron, cut the 14th wire.
//                If the 14th wire is coral, cut the 8th wire.
//                If there are exactly 19 silver wires, cut the 20th wire.
//                If the 20th wire is rust, cut the 5th wire.
//                If there are exactly 18 emerald wires, cut the 14th wire.
//                If there are exactly 2 latte wires, cut the 2nd wire.
//                If the 19th wire is tan, cut the 11th wire.
//                If the 9th wire is basil, cut the 17th wire.
//                If there are exactly 12 red wires, cut the 5th wire.
//                If there are more than 15 plum wires, cut the 18th wire.
//                If the 19th wire is chiffon, cut the 15th wire.
//                If there are exactly 18 tiger wires, cut the 8th wire.
//                If there are exactly 15 sky wires, cut the 5th wire.
//                If there are exactly 20 gray wires, cut the 12th wire.
//                If the 12th wire is penny, cut the 11th wire.
//                If there are exactly 20 carrot wires, cut the 18th wire.
//                If there are exactly 5 shortbread wires, cut the 19th wire.
//                If there are exactly 4 rouge wires, cut the 6th wire.
//                If there are exactly 10 rust wires, cut the 14th wire.
//                If there are exactly 2 iris wires, cut the 15th wire.
//                If there are exactly 12 arctic wires, cut the 15th wire.
//                If there are exactly 8 berry wires, cut the 2nd wire.
//                If there are exactly 2 iris wires, cut the 14th wire.
//                If there is exactly 1 cloud wire, cut the 5th wire.
//                If the 10th wire is eggplant, cut the 16th wire.
//                If the 18th wire is hickory, cut the 10th wire.
//                If the 1st wire is crepe, cut the 20th wire.
//                If there are more than 16 green wires, cut the 3rd wire.
//                If there are exactly 17 blood wires, cut the 19th wire.
//                If there are exactly 7 lime wires, cut the 18th wire.
//                If there are exactly 16 bone wires, cut the 8th wire.
//                If there are more than 16 ivory wires, cut the 9th wire.
//                If there are more than 7 ash wires, cut the 19th wire.
//                If there are exactly 19 watermelon wires, cut the 9th wire.
//                If there are more than 13 silver wires, cut the 7th wire.
//                If the 1st wire is navy, cut the 18th wire.
//                If the 4th wire is coral, cut the 10th wire.
//                If the 12th wire is eggplant, cut the 11th wire.
//                If there are exactly 5 apricot wires, cut the 20th wire.
//                If there are more than 14 gray wires, cut the 9th wire.
//                If the 15th wire is tortilla, cut the 8th wire.
//                If there are exactly 3 pear wires, cut the 14th wire.
//                If there are exactly 3 chiffon wires, cut the 2nd wire.
//                If there are exactly 11 watermelon wires, cut the 13th wire.
//                If the 10th wire is crimson, cut the 13th wire.
//                If there are exactly 12 fawn wires, cut the 5th wire.
//                If there are more than 15 violet wires, cut the 9th wire.
//                If there are exactly 6 arctic wires, cut the 9th wire.
//                If the 7th wire is shamrock, cut the 4th wire.
//                If there are exactly 18 watermelon wires, cut the 18th wire.
//                If there are exactly 18 teal wires, cut the 18th wire.
//                If there are exactly 17 watermelon wires, cut the 9th wire.
//                If there are more than 13 rust wires, cut the 7th wire.
//                If there is exactly 1 oyster wire, cut the 4th wire.
//                If there are exactly 4 oyster wires, cut the 19th wire.
//                If there are exactly 13 fern wires, cut the 8th wire.
//                If the 11th wire is magenta, cut the 10th wire.
//                If there are exactly 11 pineapple wires, cut the 7th wire.
//                If there are exactly 16 scarlet wires, cut the 18th wire.
//                If the 20th wire is sky, cut the 6th wire.
//                If there are exactly 4 lapis wires, cut the 13th wire.
//                If the 3rd wire is arctic, cut the 3rd wire.
//                If the 8th wire is blue, cut the 1st wire.
//                If the 16th wire is pear, cut the 11th wire.
//                If there are exactly 4 charcoal wires, cut the 13th wire.
//                If there are exactly 9 stone wires, cut the 18th wire.
//                If there are exactly 10 ruby wires, cut the 3rd wire.
//                If there are exactly 17 peach wires, cut the 17th wire.
//                If the 12th wire is stone, cut the 9th wire.
//                If there are exactly 3 silver wires, cut the 16th wire.
//                If the 3rd wire is azure, cut the 20th wire.
//                If there are exactly 15 caramel wires, cut the 13th wire.
//                If the 16th wire is dijon, cut the 6th wire.
//                If there are exactly 3 watermelon wires, cut the 2nd wire.
//                If there are exactly 7 teal wires, cut the 13th wire.
//                If the 7th wire is peach, cut the 20th wire.
//                If the 10th wire is blush, cut the 1st wire.
//                If there are exactly 13 granola wires, cut the 1st wire.
//                If the 19th wire is coconut, cut the 1st wire.
//                If there are exactly 4 gold wires, cut the 1st wire.
//                If there are more than 15 blue wires, cut the 8th wire.
//                If there are more than 10 navy wires, cut the 4th wire.
//                If there are exactly 7 magenta wires, cut the 6th wire.
//                If there are exactly 10 pink wires, cut the 4th wire.
//                If there are exactly 12 blush wires, cut the 5th wire.
//                If there are exactly 13 azure wires, cut the 2nd wire.
//                If the 16th wire is cobalt, cut the 20th wire.
//                If the 17th wire is jade, cut the 17th wire.
//                If the 19th wire is bone, cut the 19th wire.
//                If there are exactly 6 gold wires, cut the 11th wire.
//                If the 18th wire is granola, cut the 12th wire.
//                If there are exactly 7 butter wires, cut the 9th wire.
//                If the 13th wire is stone, cut the 16th wire.
//                If the 11th wire is green, cut the 11th wire.
//                If there are exactly 9 emerald wires, cut the 11th wire.
//                If there are exactly 3 basil wires, cut the 8th wire.
//                If the 16th wire is ruby, cut the 9th wire.
//                If the 18th wire is white, cut the 7th wire.
//                If the 14th wire is seaweed, cut the 2nd wire.
//                If there is exactly 1 cobalt wire, cut the 7th wire.
//                If there are exactly 17 sky wires, cut the 17th wire.
//                If there are more than 15 bubblegum wires, cut the 3rd wire.
//                If the 13th wire is arctic, cut the 3rd wire.
//                If the 19th wire is pear, cut the 17th wire.
//                If there are exactly 12 cherry wires, cut the 5th wire.
//                If the 10th wire is hazelnut, cut the 9th wire.
//                If the 9th wire is tortilla, cut the 10th wire.
//                If the 12th wire is pineapple, cut the 15th wire.
//                If there are exactly 4 frost wires, cut the 19th wire.
//                If there are exactly 20 shortbread wires, cut the 17th wire.
//                If there are more than 9 bronze wires, cut the 11th wire.
//                If the 9th wire is ivory, cut the 13th wire.
//                If the 8th wire is pearl, cut the 7th wire.
//                If the 13th wire is scarlet, cut the 11th wire.
//                If there are exactly 7 cotton wires, cut the 12th wire.
//                If the 3rd wire is seaweed, cut the 6th wire.
//                If the 20th wire is iron, cut the 9th wire.
//                If there are exactly 7 banana wires, cut the 1st wire.
//                If the 19th wire is berry, cut the 14th wire.
//                If there is exactly 1 teal wire, cut the 1st wire.
//                If the 5th wire is coral, cut the 5th wire.
//                If there are exactly 4 cotton wires, cut the 13th wire.
//                If there are exactly 9 stone wires, cut the 5th wire.
//                If there are exactly 3 teal wires, cut the 1st wire.
//                If there is exactly 1 crimson wire, cut the 15th wire.
//                If the 8th wire is grape, cut the 5th wire.
//                If the 6th wire is hickory, cut the 14th wire.
//                If there are exactly 18 corn wires, cut the 14th wire.
//                If there are more than 14 wine wires, cut the 6th wire.
//                If there are exactly 2 cotton wires, cut the 3rd wire.
//                If the 4th wire is iris, cut the 10th wire.
//                If there are exactly 5 coconut wires, cut the 20th wire.
//                If there are exactly 2 honey wires, cut the 10th wire.
//                If there are exactly 2 granola wires, cut the 3rd wire.
//                If the 3rd wire is jade, cut the 7th wire.
//                If there are exactly 4 sky wires, cut the 6th wire.
//                If the 1st wire is caramel, cut the 4th wire.
//                If the 19th wire is coral, cut the 9th wire.
//                If there are exactly 8 latte wires, cut the 9th wire.
//                If there are exactly 7 azure wires, cut the 19th wire.
//                If the 5th wire is yellow, cut the 15th wire.
//                If there are more than 15 pineapple wires, cut the 17th wire.
//                If the 1st wire is navy, cut the 1st wire.
//                If there are exactly 10 peach wires, cut the 1st wire.
//                If the 1st wire is coconut, cut the 14th wire.
//                If the 13th wire is apricot, cut the 16th wire.
//                If there are exactly 12 black wires, cut the 6th wire.
//                If there are exactly 12 azure wires, cut the 17th wire.
//                If there are exactly 11 azure wires, cut the 9th wire.
//                If there are exactly 8 ash wires, cut the 20th wire.
//                If there are exactly 19 orchid wires, cut the 5th wire.
//                If there are exactly 6 lapis wires, cut the 4th wire.
//                If the 7th wire is penny, cut the 2nd wire.
//                If there are exactly 20 apricot wires, cut the 3rd wire.
//                If there are exactly 6 latte wires, cut the 16th wire.
//                If there are more than 14 butter wires, cut the 6th wire.
//                If there are exactly 20 sky wires, cut the 3rd wire.
//                If the 3rd wire is seaweed, cut the 12th wire.
//                If the 6th wire is magenta, cut the 4th wire.
//                If there are exactly 13 apricot wires, cut the 19th wire.
//                If there are more than 9 lead wires, cut the 6th wire.
//                If there are exactly 19 green wires, cut the 5th wire.
//                If there are exactly 7 tan wires, cut the 15th wire.
//                If the 12th wire is silver, cut the 8th wire.
//                If the 3rd wire is penny, cut the 2nd wire.
//                If the 20th wire is porcelain, cut the 18th wire.
//                If the 16th wire is chocolate, cut the 19th wire.
//                If the 10th wire is fawn, cut the 7th wire.
//                If there are exactly 14 marmalade wires, cut the 20th wire.
//                If the 14th wire is cotton, cut the 6th wire.
//                If the 11th wire is emerald, cut the 13th wire.
//                If there are exactly 9 tan wires, cut the 14th wire.
//                If the 16th wire is white, cut the 11th wire.
//                If there are exactly 19 fawn wires, cut the 15th wire.
//                If there are exactly 3 butter wires, cut the 19th wire.
//                If there are exactly 13 squash wires, cut the 11th wire.
//                If there are exactly 6 carrot wires, cut the 18th wire.
//                If there are exactly 17 ruby wires, cut the 5th wire.
//                If there are exactly 15 caramel wires, cut the 19th wire.
//                If the 19th wire is pineapple, cut the 2nd wire.
//                If there are exactly 2 marmalade wires, cut the 2nd wire.
//                If the 12th wire is marigold, cut the 16th wire.
//                If there are exactly 17 sage wires, cut the 18th wire.
//                If there are exactly 13 grape wires, cut the 17th wire.
//                If there is exactly 1 granola wire, cut the 6th wire.
//                If there are exactly 19 teal wires, cut the 10th wire.
//                If the 18th wire is purple, cut the 10th wire.
//                If there are exactly 4 sandstone wires, cut the 20th wire.
//                If the 3rd wire is penny, cut the 10th wire.
//                If there are exactly 5 orange wires, cut the 19th wire.
//                If there are exactly 10 coral wires, cut the 19th wire.
//                If there are more than 16 sandstone wires, cut the 2nd wire.
//                If there are exactly 10 purple wires, cut the 17th wire.
//                If there are more than 14 lime wires, cut the 7th wire.
//                If there are exactly 6 lime wires, cut the 19th wire.
//                If there are exactly 5 shamrock wires, cut the 13th wire.
//                If there are exactly 13 blood wires, cut the 8th wire.
//                If there are more than 18 granola wires, cut the 19th wire.
//                If there are exactly 7 blood wires, cut the 16th wire.
//                If there are more than 12 banana wires, cut the 9th wire.
//                If the 8th wire is jade, cut the 12th wire.
//                If there are exactly 7 cherry wires, cut the 15th wire.
//                If there are exactly 18 flint wires, cut the 1st wire.
//                If the 5th wire is cherry, cut the 9th wire.
//                If there are more than 17 lemon wires, cut the 4th wire.
//                If there are exactly 5 chocolate wires, cut the 13th wire.
//                If there are exactly 6 bronze wires, cut the 5th wire.
//                If there are exactly 18 magenta wires, cut the 4th wire.
//                If the 9th wire is bronze, cut the 10th wire.
//                If the 4th wire is brown, cut the 7th wire.
//                If the 4th wire is watermelon, cut the 7th wire.
//                If the 17th wire is carrot, cut the 16th wire.
//                If there are more than 11 berry wires, cut the 19th wire.
//                If the 15th wire is bone, cut the 4th wire.
//                If there are exactly 4 coconut wires, cut the 1st wire.
//                If there are exactly 18 granola wires, cut the 11th wire.
//                If the 20th wire is pink, cut the 13th wire.
//                If the 7th wire is flint, cut the 4th wire.
//                If there are exactly 19 cobalt wires, cut the 16th wire.
//                If there is exactly 1 berry wire, cut the 2nd wire.
//                If the 12th wire is ash, cut the 7th wire.
//                If there are exactly 16 carrot wires, cut the 7th wire.
//                If the 20th wire is rosewood, cut the 8th wire.
//                If the 19th wire is butter, cut the 8th wire.
//                If the 17th wire is granola, cut the 19th wire.
//                If there are exactly 15 bubblegum wires, cut the 18th wire.
//                If there are exactly 14 jade wires, cut the 4th wire.
//                If there are exactly 8 black wires, cut the 12th wire.
//                If there are exactly 6 silver wires, cut the 9th wire.
//                If there are exactly 20 hazelnut wires, cut the 12th wire.
//                If there are exactly 19 wine wires, cut the 11th wire.
//                If the 12th wire is pink, cut the 5th wire.
//                If the 5th wire is teal, cut the 7th wire.
//                If there are exactly 8 chiffon wires, cut the 5th wire.
//                If there are exactly 8 butter wires, cut the 8th wire.
//                If there are exactly 8 honey wires, cut the 17th wire.
//                If the 8th wire is wine, cut the 15th wire.
//                If there are more than 7 bubblegum wires, cut the 14th wire.
//                If there are exactly 11 stone wires, cut the 20th wire.
//                If the 15th wire is ruby, cut the 7th wire.
//                If there are exactly 6 penny wires, cut the 11th wire.
//                If there are exactly 15 arctic wires, cut the 6th wire.
//                If there are exactly 14 hickory wires, cut the 1st wire.
//                If the 11th wire is arctic, cut the 16th wire.
//                If there are exactly 3 red wires, cut the 12th wire.
//                If the 16th wire is pear, cut the 15th wire.
//                If the 2nd wire is cloud, cut the 14th wire.
//                If there are exactly 19 rose wires, cut the 10th wire.
//                If there are exactly 5 marmalade wires, cut the 8th wire.
//                If there are exactly 18 daisy wires, cut the 1st wire.
//                If the 9th wire is corn, cut the 13th wire.
//                If there are exactly 13 pearl wires, cut the 13th wire.
//                If the 18th wire is obsidian, cut the 20th wire.
//                If there are exactly 10 silver wires, cut the 17th wire.
//                If there are exactly 12 shortbread wires, cut the 13th wire.
//                If the 16th wire is yellow, cut the 9th wire.
//                If the 2nd wire is charcoal, cut the 18th wire.
//                If there are exactly 15 ash wires, cut the 8th wire.
//                If the 3rd wire is shamrock, cut the 8th wire.
//                If there are exactly 13 sandstone wires, cut the 5th wire.
//                If there are exactly 8 black wires, cut the 19th wire.
//                If the 20th wire is wood, cut the 7th wire.
//                If there are exactly 18 pearl wires, cut the 19th wire.
//                If there are exactly 20 rouge wires, cut the 1st wire.
//                If there are exactly 2 bronze wires, cut the 9th wire.
//                If there are exactly 15 azure wires, cut the 10th wire.
//                If the 19th wire is candy, cut the 8th wire.
//                If there are exactly 14 macaroon wires, cut the 16th wire.
//                If the 20th wire is pear, cut the 10th wire.
//                If there are exactly 4 coral wires, cut the 8th wire.
//                If the 19th wire is scarlet, cut the 5th wire.
//                If there are exactly 19 orchid wires, cut the 15th wire.
//                If there are exactly 16 plum wires, cut the 2nd wire.
//                If there are exactly 5 charcoal wires, cut the 12th wire.
//                If there are exactly 19 latte wires, cut the 12th wire.
//                If there are exactly 5 wood wires, cut the 2nd wire.
//                If there are exactly 5 orchid wires, cut the 7th wire.
//                If the 17th wire is corn, cut the 10th wire.
//                If there are exactly 14 bone wires, cut the 10th wire.
//                If there are more than 11 bubblegum wires, cut the 16th wire.
//                If there are exactly 18 fire wires, cut the 17th wire.
//                If there are exactly 14 white wires, cut the 1st wire.
//                If there are exactly 2 cherry wires, cut the 16th wire.
//                If the 12th wire is cotton, cut the 16th wire.
//                If the 10th wire is carrot, cut the 17th wire.
//                If the 14th wire is banana, cut the 8th wire.
//                If there are exactly 7 fire wires, cut the 8th wire.
//                If the 2nd wire is blood, cut the 2nd wire.
//                If the 13th wire is lemon, cut the 3rd wire.
//                If there are exactly 16 plum wires, cut the 18th wire.
//                If the 20th wire is cotton, cut the 20th wire.
//                If there are more than 13 black wires, cut the 6th wire.
//                If there are exactly 18 shamrock wires, cut the 20th wire.
//                If there are more than 13 lime wires, cut the 12th wire.
//                If the 3rd wire is apricot, cut the 17th wire.
//                If there are exactly 20 magenta wires, cut the 11th wire.
//                If there are exactly 6 azure wires, cut the 2nd wire.
//                If there are exactly 11 latte wires, cut the 19th wire.
//                If the 5th wire is coconut, cut the 20th wire.
//                If there are exactly 5 ivory wires, cut the 6th wire.
//                If there are exactly 4 bubblegum wires, cut the 20th wire.
//                If there are exactly 4 tan wires, cut the 13th wire.
//                If there are exactly 3 fawn wires, cut the 18th wire.
//                If there are more than 9 gold wires, cut the 10th wire.
//                If there are exactly 10 blue wires, cut the 1st wire.
//                If there are exactly 4 macaroon wires, cut the 1st wire.
//                If the 7th wire is magenta, cut the 14th wire.
//                If there are exactly 17 candy wires, cut the 17th wire.
//                If the 10th wire is daisy, cut the 3rd wire.
//                If there are exactly 8 chocolate wires, cut the 3rd wire.
//                If the 16th wire is tan, cut the 12th wire.
//                If there is exactly 1 tan wire, cut the 3rd wire.
//                If the 15th wire is bronze, cut the 18th wire.
//                If there are exactly 18 daisy wires, cut the 5th wire.
//                If there are exactly 13 hazelnut wires, cut the 12th wire.
//                If the 12th wire is charcoal, cut the 12th wire.
//                If there are exactly 16 orchid wires, cut the 9th wire.
//                If the 10th wire is cotton, cut the 17th wire.
//                If there are exactly 17 carrot wires, cut the 6th wire.
//                If there are exactly 13 sky wires, cut the 9th wire.
//                If the 20th wire is tortilla, cut the 13th wire.
//                If there are exactly 16 shamrock wires, cut the 3rd wire.
//                If the 8th wire is lapis, cut the 6th wire.
//                If the 18th wire is cherry, cut the 10th wire.
//                If there are exactly 10 wood wires, cut the 1st wire.
//                If there are exactly 4 hazelnut wires, cut the 5th wire.
//                If there are exactly 12 tan wires, cut the 9th wire.
//                If there are exactly 12 corn wires, cut the 8th wire.
//                If there are exactly 3 iris wires, cut the 20th wire.
//                If there are exactly 5 jade wires, cut the 3rd wire.
//                If the 13th wire is plum, cut the 18th wire.
//                If the 13th wire is brick, cut the 11th wire.
//                If there are exactly 3 banana wires, cut the 8th wire.
//                If the 17th wire is cloud, cut the 2nd wire.
//                If there are exactly 19 coral wires, cut the 13th wire.
//                If there are exactly 16 plum wires, cut the 15th wire.
//                If there are more than 8 daisy wires, cut the 19th wire.
//                If there are exactly 16 rouge wires, cut the 18th wire.
//                If there are exactly 19 lemon wires, cut the 2nd wire.
//                If there are exactly 10 red wires, cut the 11th wire.
//                If there are exactly 6 sky wires, cut the 4th wire.
//                If there are exactly 8 red wires, cut the 8th wire.
//                If there are exactly 7 pearl wires, cut the 1st wire.
//                If the 18th wire is charcoal, cut the 20th wire.
//                If there are exactly 9 brown wires, cut the 20th wire.
//                If there are exactly 11 caramel wires, cut the 7th wire.
//                If the 9th wire is azure, cut the 8th wire.
//                If there are exactly 16 eggplant wires, cut the 7th wire.
//                If the 10th wire is oyster, cut the 1st wire.
//                If there are exactly 6 magenta wires, cut the 10th wire.
//                If the 18th wire is shamrock, cut the 6th wire.
//                If there are exactly 13 black wires, cut the 19th wire.
//                If the 11th wire is fire, cut the 12th wire.
//                If there are exactly 18 plum wires, cut the 17th wire.
//                If there are more than 12 charcoal wires, cut the 10th wire.
//                If there is exactly 1 granola wire, cut the 14th wire.
//                If the 2nd wire is honey, cut the 20th wire.
//                If there are exactly 5 stone wires, cut the 9th wire.
//                If there are more than 7 navy wires, cut the 6th wire.
//                If the 2nd wire is dijon, cut the 4th wire.
//                If there are exactly 20 cherry wires, cut the 18th wire.
//                If there are exactly 17 ash wires, cut the 19th wire.
//                If there is less than 1 gold wire, cut the 9th wire.
//                If the 1st wire is pear, cut the 19th wire.
//                If there are exactly 18 white wires, cut the 9th wire.
//                If there are more than 19 scarlet wires, cut the 10th wire.
//                If the 8th wire is porcelain, cut the 18th wire.
//                If there are exactly 2 yellow wires, cut the 10th wire.
//                If the 13th wire is emerald, cut the 20th wire.
//                If the 1st wire is azure, cut the 12th wire.
//                If the 3rd wire is scarlet, cut the 12th wire.
//                If there are exactly 9 cobalt wires, cut the 1st wire.
//                If there are exactly 4 arctic wires, cut the 19th wire.
//                If there are exactly 17 arctic wires, cut the 13th wire.
//                If there are exactly 5 wood wires, cut the 19th wire.
//                If the 19th wire is berry, cut the 1st wire.
//                If the 1st wire is ash, cut the 1st wire.
//                If there are exactly 6 sandstone wires, cut the 19th wire.
//                If there are exactly 14 flint wires, cut the 18th wire.
//                If there are exactly 14 crimson wires, cut the 19th wire.
//                If the 6th wire is oyster, cut the 20th wire.
//                If the 4th wire is emerald, cut the 13th wire.
//                If there are exactly 13 stone wires, cut the 15th wire.
//                If there are exactly 7 pear wires, cut the 3rd wire.
//                If the 1st wire is pickle, cut the 20th wire.
//                If the 7th wire is lead, cut the 16th wire.
//                If the 6th wire is wine, cut the 14th wire.
//                If there are exactly 15 bone wires, cut the 8th wire.
//                If there are exactly 4 black wires, cut the 17th wire.
//                If the 3rd wire is arctic, cut the 1st wire.
//                If the 1st wire is iron, cut the 16th wire.
//                If the 3rd wire is tan, cut the 15th wire.
//                If the 8th wire is purple, cut the 3rd wire.
//                If there are exactly 16 candy wires, cut the 15th wire.
//                If the 2nd wire is grape, cut the 13th wire.
//                If there are exactly 9 eggplant wires, cut the 20th wire.
//                If there are exactly 4 coral wires, cut the 18th wire.
//                If the 9th wire is seaweed, cut the 2nd wire.
//                If the 1st wire is squash, cut the 16th wire.
//                If the 13th wire is apricot, cut the 10th wire.
//                If the 8th wire is fawn, cut the 17th wire.
//                If the 15th wire is penny, cut the 15th wire.
//                If the 3rd wire is rose, cut the 19th wire.
//                If the 11th wire is tiger, cut the 11th wire.
//                If there are exactly 3 yellow wires, cut the 19th wire.
//                If there are exactly 14 rose wires, cut the 6th wire.
//                If there are exactly 7 honey wires, cut the 13th wire.
//                If there are exactly 12 blue wires, cut the 19th wire.
//                If there are more than 11 green wires, cut the 5th wire.
//                If the 9th wire is shamrock, cut the 20th wire.
//                If the 10th wire is rosewood, cut the 6th wire.
//                If there are exactly 6 banana wires, cut the 19th wire.
//                If the 19th wire is teal, cut the 10th wire.
//                If the 20th wire is corn, cut the 6th wire.
//                If there are exactly 10 bronze wires, cut the 19th wire.
//                If there are exactly 13 pear wires, cut the 12th wire.
//                If there are exactly 6 white wires, cut the 5th wire.
//                If the 9th wire is caramel, cut the 18th wire.
//                If the 18th wire is corn, cut the 13th wire.
//                If the 19th wire is plum, cut the 18th wire.
//                If there are more than 16 banana wires, cut the 20th wire.
//                If the 7th wire is frost, cut the 18th wire.
//                If there are exactly 5 ash wires, cut the 8th wire.
//                If there are exactly 4 bronze wires, cut the 2nd wire.
//                If there are more than 14 cherry wires, cut the 7th wire.
//                If the 6th wire is cobalt, cut the 14th wire.
//                If there are exactly 16 obsidian wires, cut the 15th wire.
//                If the 11th wire is pickle, cut the 14th wire.
//                If there are exactly 7 blue wires, cut the 7th wire.
//                If there are exactly 2 ivory wires, cut the 15th wire.
//                If the 10th wire is red, cut the 6th wire.
//                If the 4th wire is bubblegum, cut the 13th wire.
//                If the 5th wire is penny, cut the 13th wire.
//                If there are exactly 8 macaroon wires, cut the 20th wire.
//                If there are exactly 10 peach wires, cut the 9th wire.
//                If there are exactly 3 amethyst wires, cut the 15th wire.
//                If the 4th wire is seaweed, cut the 3rd wire.
//                If there are exactly 8 sand wires, cut the 3rd wire.
//                If the 19th wire is wine, cut the 5th wire.
//                If there are exactly 3 fern wires, cut the 18th wire.
//                If there are exactly 19 bubblegum wires, cut the 18th wire.
//                If the 6th wire is shortbread, cut the 9th wire.
//                If there are more than 19 coffee wires, cut the 13th wire.
//                If there are exactly 16 bone wires, cut the 15th wire.
//                If the 9th wire is pearl, cut the 13th wire.
//                If there are exactly 8 grape wires, cut the 20th wire.
//                If the 11th wire is violet, cut the 18th wire.
//                If there are exactly 4 pineapple wires, cut the 3rd wire.
//                If there are exactly 20 tan wires, cut the 19th wire.
//                If the 8th wire is gold, cut the 17th wire.
//                If the 16th wire is tiger, cut the 11th wire.
//                If the 13th wire is bone, cut the 17th wire.
//                If there are exactly 5 squash wires, cut the 19th wire.
//                If the 12th wire is magenta, cut the 8th wire.
//                If there are exactly 13 orchid wires, cut the 20th wire.
//                If the 17th wire is ivory, cut the 11th wire.
//                If the 16th wire is pear, cut the 5th wire.
//                If there are exactly 6 basil wires, cut the 2nd wire.
//                If there are more than 9 charcoal wires, cut the 8th wire.
//                If the 5th wire is banana, cut the 5th wire.
//                If the 19th wire is carrot, cut the 1st wire.
//                If there are exactly 19 crimson wires, cut the 4th wire.
//                If the 18th wire is marmalade, cut the 17th wire.
//                If there are exactly 19 iron wires, cut the 5th wire.
//                If the 9th wire is pink, cut the 20th wire.
//                If there are exactly 15 cobalt wires, cut the 5th wire.
//                If there are more than 14 fire wires, cut the 2nd wire.
//                If there are exactly 13 teal wires, cut the 8th wire.
//                If the 2nd wire is cherry, cut the 2nd wire.
//                If the 12th wire is green, cut the 18th wire.
//                If there are more than 14 white wires, cut the 19th wire.
//                If there are exactly 18 red wires, cut the 16th wire.
//                If the 20th wire is rose, cut the 20th wire.
//                If the 1st wire is rust, cut the 9th wire.
//                If there are exactly 15 amethyst wires, cut the 11th wire.
//                If there are exactly 10 coffee wires, cut the 2nd wire.
//                If there are exactly 3 green wires, cut the 18th wire.
//                If there are exactly 2 orchid wires, cut the 2nd wire.
//                If the 3rd wire is violet, cut the 15th wire.
//                If there are exactly 2 gold wires, cut the 16th wire.
//                If the 18th wire is ivory, cut the 17th wire.
//                If there are exactly 15 penny wires, cut the 19th wire.
//                If the 1st wire is corn, cut the 5th wire.
//                If there are more than 19 lemon wires, cut the 8th wire.
//                If there are exactly 9 wood wires, cut the 2nd wire.
//                If the 1st wire is shortbread, cut the 2nd wire.
//                If there are exactly 11 seaweed wires, cut the 14th wire.
//                If the 19th wire is cotton, cut the 9th wire.
//                If there are exactly 20 fern wires, cut the 20th wire.
//                If there are exactly 20 charcoal wires, cut the 1st wire.
//                If there are exactly 15 rose wires, cut the 6th wire.
//                If the 7th wire is arctic, cut the 15th wire.
//                If there are exactly 20 obsidian wires, cut the 7th wire.
//                If the 7th wire is candy, cut the 4th wire.
//                If there are exactly 20 granola wires, cut the 20th wire.
//                If there are more than 15 brown wires, cut the 15th wire.
//                If there are exactly 6 oyster wires, cut the 14th wire.
//                If the 1st wire is flint, cut the 11th wire.
//                If there are exactly 13 tan wires, cut the 2nd wire.
//                If there are exactly 14 granola wires, cut the 16th wire.
//                If there are exactly 7 squash wires, cut the 13th wire.
//                If the 8th wire is blood, cut the 3rd wire.
//                If the 17th wire is dijon, cut the 16th wire.
//                If the 2nd wire is buttermilk, cut the 6th wire.
//                If there are more than 16 grape wires, cut the 8th wire.
//                If there are exactly 18 sand wires, cut the 11th wire.
//                If there are exactly 2 lemon wires, cut the 17th wire.
//                If there are exactly 3 rosewood wires, cut the 15th wire.
//                If the 12th wire is chocolate, cut the 14th wire.
//                If there are exactly 7 gold wires, cut the 6th wire.
//                If the 12th wire is bubblegum, cut the 6th wire.
//                If there are exactly 4 eggplant wires, cut the 7th wire.
//                If there are exactly 20 crimson wires, cut the 9th wire.
//                If there are exactly 11 yellow wires, cut the 20th wire.
//                If there are exactly 13 coconut wires, cut the 15th wire.
//                If there are more than 18 pickle wires, cut the 19th wire.
//                If there are exactly 9 ruby wires, cut the 4th wire.
//                If the 2nd wire is green, cut the 7th wire.
//                If the 6th wire is candy, cut the 1st wire.
//                If the 10th wire is bronze, cut the 1st wire.
//                If there are more than 11 lapis wires, cut the 12th wire.
//                If there are more than 17 frost wires, cut the 18th wire.
//                If there are exactly 16 green wires, cut the 13th wire.
//                If there are exactly 5 crimson wires, cut the 8th wire.
//                If the 10th wire is bronze, cut the 11th wire.
//                If there are exactly 12 marmalade wires, cut the 2nd wire.
//                If there are more than 10 sage wires, cut the 1st wire.
//                If the 13th wire is watermelon, cut the 17th wire.
//                If the 5th wire is purple, cut the 16th wire.
//                If there are exactly 11 cotton wires, cut the 1st wire.
//                If there are more than 19 ash wires, cut the 19th wire.
//                If the 20th wire is frost, cut the 3rd wire.
//                If there are exactly 9 wine wires, cut the 20th wire.
//                If there are exactly 15 banana wires, cut the 19th wire.
//                If there are more than 14 violet wires, cut the 2nd wire.
//                If there are exactly 8 azure wires, cut the 2nd wire.
//                If the 8th wire is purple, cut the 15th wire.
//                If the 14th wire is dijon, cut the 13th wire.
//                If there are exactly 10 ivory wires, cut the 7th wire.
//                If the 6th wire is rose, cut the 1st wire.
//                If the 14th wire is apricot, cut the 13th wire.
//                If there are exactly 14 latte wires, cut the 10th wire.
//                If there are exactly 3 magenta wires, cut the 7th wire.
//                If the 3rd wire is stone, cut the 5th wire.
//                If the 1st wire is orchid, cut the 1st wire.
//                If the 19th wire is sky, cut the 20th wire.
//                If there are exactly 19 basil wires, cut the 17th wire.
//                If there are exactly 10 fire wires, cut the 17th wire.
//                If the 17th wire is violet, cut the 5th wire.
//                If the 1st wire is orange, cut the 10th wire.
//                If there are exactly 7 berry wires, cut the 10th wire.
//                If there are exactly 8 marigold wires, cut the 19th wire.
//                If there are exactly 10 lapis wires, cut the 7th wire.
//                If there are exactly 2 chiffon wires, cut the 18th wire.
//                If the 13th wire is buttermilk, cut the 20th wire.
//                If the 5th wire is candy, cut the 5th wire.
//                If there are exactly 18 coral wires, cut the 20th wire.
//                If the 6th wire is tan, cut the 20th wire.
//                If there are exactly 14 pearl wires, cut the 12th wire.
//                If there is exactly 1 coffee wire, cut the 8th wire.
//                If there are exactly 5 rose wires, cut the 13th wire.
//                If the 5th wire is macaroon, cut the 16th wire.
//                If the 2nd wire is bone, cut the 11th wire.
//                If the 3rd wire is cobalt, cut the 15th wire.
//                If there are exactly 12 rouge wires, cut the 13th wire.
//                If the 20th wire is hickory, cut the 13th wire.
//                If there is exactly 1 marmalade wire, cut the 10th wire.
//                If there are exactly 15 latte wires, cut the 20th wire.
//                If the 9th wire is emerald, cut the 2nd wire.
//                If the 4th wire is blue, cut the 4th wire.
//                If there are exactly 19 white wires, cut the 4th wire.
//                If the 7th wire is obsidian, cut the 3rd wire.
//                If there are exactly 17 pink wires, cut the 6th wire.
//                If the 8th wire is coconut, cut the 7th wire.
//                If the 10th wire is gold, cut the 4th wire.
//                If the 8th wire is charcoal, cut the 13th wire.
//                If the 11th wire is crepe, cut the 1st wire.
//                If there are exactly 5 marmalade wires, cut the 15th wire.
//                If there are more than 18 grape wires, cut the 9th wire.
//                If the 10th wire is silver, cut the 11th wire.
//                If there are exactly 10 ivory wires, cut the 19th wire.
//                If the 14th wire is sky, cut the 19th wire.
//                If the 4th wire is pearl, cut the 8th wire.
//                If there are exactly 12 red wires, cut the 20th wire.
//                If there are more than 12 jade wires, cut the 6th wire.
//                If there are exactly 12 gray wires, cut the 18th wire.
//                If there are exactly 8 chocolate wires, cut the 17th wire.
//                If there are exactly 15 black wires, cut the 6th wire.
//                If the 6th wire is emerald, cut the 6th wire.
//                If the 15th wire is blue, cut the 8th wire.
//                If the 11th wire is butter, cut the 1st wire.
//                If there are less than 2 lime wires, cut the 12th wire.
//                If the 6th wire is sandstone, cut the 7th wire.
//                If there are exactly 16 arctic wires, cut the 15th wire.
//                If the 5th wire is lemon, cut the 18th wire.
//                If the 18th wire is pineapple, cut the 5th wire.
//                If there are exactly 9 penny wires, cut the 17th wire.
//                If there are exactly 9 buttermilk wires, cut the 3rd wire.
//                If there are exactly 20 berry wires, cut the 7th wire.
//                If there are exactly 9 basil wires, cut the 18th wire.
//                If the 18th wire is charcoal, cut the 9th wire.
//                If the 20th wire is azure, cut the 14th wire.
//                If there are exactly 20 granola wires, cut the 7th wire.
//                If there are exactly 6 plum wires, cut the 6th wire.
//                If the 7th wire is bone, cut the 3rd wire.
//                If there are exactly 6 banana wires, cut the 6th wire.
//                If there are exactly 13 charcoal wires, cut the 15th wire.
//                If the 19th wire is tan, cut the 8th wire.
//                If there are exactly 7 bronze wires, cut the 5th wire.
//                If the 2nd wire is sand, cut the 20th wire.
//                If there are exactly 2 oyster wires, cut the 5th wire.
//                If there are exactly 19 bronze wires, cut the 3rd wire.
//                If there are exactly 18 seaweed wires, cut the 16th wire.
//                If there are exactly 5 banana wires, cut the 20th wire.
//                If the 12th wire is pineapple, cut the 12th wire.
//                If the 9th wire is charcoal, cut the 3rd wire.
//                If there are more than 16 orchid wires, cut the 7th wire.
//                If there are exactly 7 candy wires, cut the 12th wire.
//                If the 4th wire is rouge, cut the 12th wire.
//                If there are exactly 3 lime wires, cut the 17th wire.
//                If there are exactly 8 crepe wires, cut the 1st wire.
//                If there are exactly 4 scarlet wires, cut the 8th wire.
//                If there are exactly 14 flint wires, cut the 6th wire.
//                If there are exactly 3 porcelain wires, cut the 14th wire.
//                If there are more than 11 coconut wires, cut the 11th wire.
//                If there are exactly 7 basil wires, cut the 5th wire.
//                If there are exactly 4 banana wires, cut the 18th wire.
//                If there are exactly 8 pickle wires, cut the 1st wire.
//                If the 7th wire is caramel, cut the 18th wire.
//                If there are more than 9 crimson wires, cut the 16th wire.
//                If there are exactly 10 ash wires, cut the 14th wire.
//                If there are more than 16 frost wires, cut the 15th wire.
//                If the 15th wire is fawn, cut the 19th wire.
//                If there are exactly 13 scarlet wires, cut the 16th wire.
//                If the 17th wire is emerald, cut the 18th wire.
//                If there are exactly 13 candy wires, cut the 5th wire.
//                If there are more than 10 brown wires, cut the 15th wire.
//                If there are exactly 11 rouge wires, cut the 10th wire.
//                If there are exactly 16 latte wires, cut the 14th wire.
//                If the 3rd wire is flint, cut the 13th wire.
//                If the 3rd wire is silver, cut the 2nd wire.
//                If there are more than 16 corn wires, cut the 15th wire.
//                If the 17th wire is wood, cut the 17th wire.
//                If there are exactly 9 fern wires, cut the 6th wire.
//                If the 4th wire is hickory, cut the 4th wire.
//                If the 12th wire is carrot, cut the 18th wire.
//                If there are exactly 3 jade wires, cut the 10th wire.
//                If there are exactly 8 penny wires, cut the 5th wire.
//                If there are exactly 14 iris wires, cut the 9th wire.
//                If there are exactly 2 watermelon wires, cut the 13th wire.
//                If the 14th wire is arctic, cut the 5th wire.
//                If there are exactly 9 blue wires, cut the 3rd wire.
//                If there are exactly 7 iris wires, cut the 8th wire.
//                If the 8th wire is ivory, cut the 4th wire.
//                If there are exactly 18 coffee wires, cut the 1st wire.
//                If the 2nd wire is wood, cut the 6th wire.
//                If there are exactly 14 arctic wires, cut the 15th wire.
//                If the 5th wire is plum, cut the 8th wire.
//                If there are exactly 4 brown wires, cut the 20th wire.
//                If there are exactly 11 rouge wires, cut the 3rd wire.
//                If there are more than 14 daisy wires, cut the 19th wire.
//                If the 11th wire is emerald, cut the 17th wire.
//                If the 5th wire is violet, cut the 1st wire.
//                If there are exactly 9 brown wires, cut the 3rd wire.
//                If the 12th wire is ivory, cut the 19th wire.
//                If the 5th wire is berry, cut the 3rd wire.
//                If there are more than 10 pickle wires, cut the 4th wire.
//                If there are exactly 9 rouge wires, cut the 1st wire.
//                If the 18th wire is amethyst, cut the 5th wire.
//                If the 8th wire is coffee, cut the 8th wire.
//                If the 9th wire is fawn, cut the 2nd wire.
//                If there are exactly 11 obsidian wires, cut the 7th wire.
//                If there are more than 12 tiger wires, cut the 1st wire.
//                If there are exactly 3 granola wires, cut the 8th wire.
//                If there are exactly 12 purple wires, cut the 5th wire.
//                If there are more than 17 rose wires, cut the 10th wire.
//                If the 13th wire is lemon, cut the 4th wire.
//                If the 14th wire is gold, cut the 11th wire.
//                If the 1st wire is apricot, cut the 17th wire.
//                If there are exactly 3 black wires, cut the 2nd wire.
//                If the 15th wire is magenta, cut the 12th wire.
//                If there is exactly 1 lead wire, cut the 20th wire.
//                If the 10th wire is sandstone, cut the 16th wire.
//                If there are exactly 8 blood wires, cut the 11th wire.
//                If there is exactly 1 frost wire, cut the 11th wire.
//                If the 5th wire is hickory, cut the 13th wire.
//                If there are exactly 20 white wires, cut the 20th wire.
//                If there are more than 14 purple wires, cut the 11th wire.
//                If there are exactly 5 latte wires, cut the 3rd wire.
//                If there is exactly 1 iron wire, cut the 8th wire.
//                If the 2nd wire is cotton, cut the 2nd wire.
//                If the 17th wire is lapis, cut the 18th wire.
//                If there are exactly 4 sky wires, cut the 19th wire.
//                If the 18th wire is jade, cut the 5th wire.
//                If there are exactly 15 black wires, cut the 4th wire.
//                If there are exactly 8 rosewood wires, cut the 14th wire.
//                If the 13th wire is gray, cut the 4th wire.
//                If the 14th wire is chocolate, cut the 14th wire.
//                If the 6th wire is silver, cut the 3rd wire.
//                If there are exactly 10 oyster wires, cut the 1st wire.
//                If the 20th wire is white, cut the 3rd wire.
//                If the 20th wire is ivory, cut the 10th wire.
//                If the 11th wire is stone, cut the 19th wire.
//                If the 2nd wire is gray, cut the 19th wire.
//                If there are exactly 2 lead wires, cut the 6th wire.
//                If the 2nd wire is shortbread, cut the 16th wire.
//                If there are exactly 16 crepe wires, cut the 4th wire.
//                If there are exactly 3 buttermilk wires, cut the 2nd wire.
//                If there are exactly 15 bone wires, cut the 15th wire.
//                If the 18th wire is apricot, cut the 3rd wire.
//                If there are exactly 15 peach wires, cut the 15th wire.
//                If the 15th wire is latte, cut the 20th wire.
//                If the 2nd wire is arctic, cut the 8th wire.
//                If there are exactly 17 rust wires, cut the 15th wire.
//                If the 15th wire is cobalt, cut the 5th wire.
//                If there are more than 17 pineapple wires, cut the 9th wire.
//                If the 20th wire is violet, cut the 18th wire.
//                If there are more than 9 grape wires, cut the 9th wire.
//                If there are exactly 2 porcelain wires, cut the 10th wire.
//                If there are exactly 14 crepe wires, cut the 10th wire.
//                If the 10th wire is butter, cut the 6th wire.
//                If there are exactly 2 pineapple wires, cut the 19th wire.
//                If there are exactly 10 teal wires, cut the 11th wire.
//                If the 18th wire is cloud, cut the 6th wire.
//                If the 18th wire is cherry, cut the 14th wire.
//                If there are exactly 11 stone wires, cut the 9th wire.
//                If there are exactly 15 rust wires, cut the 15th wire.
//                If the 16th wire is scarlet, cut the 6th wire.
//                If there are exactly 15 wine wires, cut the 2nd wire.
//                If there are exactly 13 shamrock wires, cut the 4th wire.
//                If there are more than 17 daisy wires, cut the 15th wire.
//                If the 19th wire is shortbread, cut the 10th wire.
//                If the 7th wire is fern, cut the 12th wire.
//                If there are exactly 9 teal wires, cut the 6th wire.
//                If there are exactly 7 coral wires, cut the 16th wire.
//                If the 9th wire is berry, cut the 18th wire.
//                If there are more than 10 butter wires, cut the 18th wire.
//                If the 9th wire is teal, cut the 6th wire.
//                If there are exactly 9 emerald wires, cut the 6th wire.
//                If there are exactly 6 cloud wires, cut the 12th wire.
//                If the 4th wire is caramel, cut the 20th wire.
//                If the 6th wire is stone, cut the 14th wire.
//                If there are exactly 18 chocolate wires, cut the 18th wire.
//                If there are exactly 15 gold wires, cut the 4th wire.
//                If there are exactly 7 ruby wires, cut the 18th wire.
//                If there are exactly 6 coconut wires, cut the 1st wire.
//                If the 7th wire is brown, cut the 16th wire.
//                If the 16th wire is carrot, cut the 14th wire.
//                If there are exactly 13 hickory wires, cut the 11th wire.
//                If there are exactly 11 stone wires, cut the 10th wire.
//                If the 10th wire is sand, cut the 4th wire.
//                If the 2nd wire is bronze, cut the 19th wire.
//                If the 2nd wire is cloud, cut the 2nd wire.
//                If there are exactly 3 grape wires, cut the 11th wire.
//                If there are exactly 7 latte wires, cut the 11th wire.
//                If there are exactly 16 jade wires, cut the 12th wire.
//                If the 18th wire is black, cut the 9th wire.
//                If the 20th wire is pear, cut the 6th wire.
//                If there are more than 8 sandstone wires, cut the 16th wire.
//                If the 13th wire is yellow, cut the 4th wire.
//                If there are exactly 20 pickle wires, cut the 3rd wire.
//                If there are exactly 7 azure wires, cut the 1st wire.
//                If the 7th wire is banana, cut the 9th wire.
//                If the 13th wire is rust, cut the 19th wire.
//                If the 5th wire is purple, cut the 18th wire.
//                If there are exactly 18 pink wires, cut the 10th wire.
//                If the 19th wire is pineapple, cut the 1st wire.
//                If the 18th wire is wine, cut the 17th wire.
//                If there are more than 8 ruby wires, cut the 13th wire.
//                If there is exactly 1 pear wire, cut the 9th wire.
//                If there are exactly 6 gold wires, cut the 13th wire.
//                If the 19th wire is macaroon, cut the 17th wire.
//                If the 11th wire is granola, cut the 4th wire.
//                If there are exactly 19 peach wires, cut the 9th wire.
//                If the 10th wire is rouge, cut the 1st wire.
//                If the 7th wire is orchid, cut the 16th wire.
//                If there are exactly 18 ivory wires, cut the 10th wire.
//                If there are exactly 17 penny wires, cut the 5th wire.
//                If the 7th wire is seaweed, cut the 14th wire.
//                If there are exactly 13 cherry wires, cut the 14th wire.
//                If the 8th wire is bronze, cut the 5th wire.
//                If there is exactly 1 green wire, cut the 13th wire.
//                If there are exactly 17 candy wires, cut the 8th wire.
//                If the 10th wire is eggplant, cut the 14th wire.
//                If there are exactly 3 rust wires, cut the 18th wire.
//                If there is exactly 1 wood wire, cut the 20th wire.
//                If there are exactly 7 pink wires, cut the 17th wire.
//                If the 13th wire is cotton, cut the 19th wire.
//                If there are exactly 15 macaroon wires, cut the 1st wire.
//                If there are more than 8 blue wires, cut the 8th wire.
//                If there are exactly 6 iron wires, cut the 4th wire.
//                If there are exactly 11 navy wires, cut the 12th wire.
//                If the 19th wire is watermelon, cut the 5th wire.
//                If there are exactly 17 fire wires, cut the 18th wire.
//                If there are exactly 2 arctic wires, cut the 1st wire.
//                If there are exactly 10 fern wires, cut the 20th wire.
//                If there are more than 16 plum wires, cut the 12th wire.
//                If there are exactly 3 tiger wires, cut the 13th wire.
//                If the 1st wire is arctic, cut the 11th wire.
//                If there are exactly 19 ruby wires, cut the 20th wire.
//                If there are exactly 10 pearl wires, cut the 4th wire.
//                If there are exactly 8 corn wires, cut the 13th wire.
//                If the 4th wire is caramel, cut the 6th wire.
//                If the 6th wire is cotton, cut the 6th wire.
//                If there are exactly 2 squash wires, cut the 12th wire.
//                If the 18th wire is orange, cut the 15th wire.
//                If there are exactly 12 yellow wires, cut the 3rd wire.
//                If the 9th wire is coral, cut the 4th wire.
//                If the 6th wire is squash, cut the 12th wire.
//                If there are exactly 2 tiger wires, cut the 8th wire.
//                If there are more than 11 green wires, cut the 17th wire.
//                If there are more than 9 wood wires, cut the 8th wire.
//                If there is exactly 1 orange wire, cut the 7th wire.
//                If there are exactly 17 butter wires, cut the 10th wire.
//                If there are exactly 6 frost wires, cut the 17th wire.
//                If there are more than 7 arctic wires, cut the 8th wire.
//                If there are more than 11 grape wires, cut the 9th wire.
//                If the 9th wire is latte, cut the 8th wire.""";
//        BufferedReader br = new BufferedReader(new StringReader(data));
//        String line;
//        while (!((line = br.readLine()) == null)) {
//            StringTokenizer st = new StringTokenizer(line);
//            st.nextToken(); //If
//            if (st.nextToken().equals("the")) {
//                String tmp = st.nextToken(); //xth
//                int num = Integer.parseInt(tmp.substring(0, tmp.length() - 2)) - 1;
//                st.nextToken(); //wire
//                st.nextToken(); //is
//                tmp = st.nextToken(); //color
//                tmp = tmp.substring(0, tmp.length() - 1); // remove comma
//                if (wires[num].equals(tmp)) { //check if true
//                    st.nextToken(); //cut
//                    st.nextToken(); //the
//                    tmp = st.nextToken(); //xth
//                    num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                    pw.println("Cut wire " + num);
//                    good = true;
//                    break;
//                }
//            } else { //there
//                st.nextToken(); //is/are
//                String tmp = st.nextToken(); //exactly, more, less
//                if (tmp.equals("exactly")) {
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    if (count(wires, tmp) == num) {
//                        st.nextToken(); //wire(s)
//                        st.nextToken(); //cut
//                        st.nextToken(); //the
//                        tmp = st.nextToken(); //xth
//                        num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                        pw.println("Cut wire " + num);
//                        good = true;
//                        break;
//                    }
//                } else if (tmp.equals("more")) {
//                    st.nextToken(); //than
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    if (count(wires, tmp) > num) {
//                        st.nextToken(); //wire(s)
//                        st.nextToken(); //cut
//                        st.nextToken(); //the
//                        tmp = st.nextToken(); //xth
//                        num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                        pw.println("Cut wire " + num);
//                        good = true;
//                        break;
//                    }
//                } else {
//                    st.nextToken(); //than
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    if (count(wires, tmp) < num) {
//                        st.nextToken(); //wire(s)
//                        st.nextToken(); //cut
//                        st.nextToken(); //the
//                        tmp = st.nextToken(); //xth
//                        num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                        pw.println("Cut wire " + num);
//                        good = true;
//                        break;
//                    }
//                }
//            }
//        }
//        if (!good) {
//            pw.println("Cut wire 1");
//        }
//
//        pw.close();
//    }
//
//    private static int count(String[] wires, String color) {
//        int cnt = 0;
//        for (String wire : wires) {
//            if (wire.equals(color)) {
//                cnt++;
//            }
//        }
//        return cnt;
//    }
//
//    private static class InputReader2 {
//        private final int BUFFER_SIZE = 1 << 16;
//        private final DataInputStream dis;
//        private final byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public InputReader2() {
//            dis = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        private String nextLine() throws IOException {
//            int c = read();
//            while (isSpaceChar(c)) {
//                c = read();
//            }
//            StringBuilder res = new StringBuilder();
//            do {
//                res.appendCodePoint(c);
//                c = read();
//            } while (!isSpaceChar(c));
//            return res.toString();
//        }
//
//        private boolean isSpaceChar(int c) {
//            return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == -1;
//        }
//
//        private void fillBuffer() throws IOException {
//            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//    }
//}
