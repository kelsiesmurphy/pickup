import { Users } from "react-feather";
import { PlusCircle } from "react-feather";
import { BarChart2 } from "react-feather";
import { heroImage } from "../assets/AssetFiles";

export const HeroConstant = {
  main_heading: "Clean up your community with Pickup",
  sub_heading:
    "Pickup helps you organise litter picking events, encouraging healthy competition between your group members as you clean up your community.",
  hero_image: heroImage,
};

export const AboutPointsConstant = [
  {
    title: "Organise events",
    description:
      "Create a new litter picking event in under 2 minutes and share before and after pictures of the cleaned areas with your group.",
    icon: PlusCircle,
  },
  {
    title: "Manage your group members",
    description:
      "Adding new members to your community is simple and allows you to keep your community up to date and happy.",
    icon: Users,
  },
  {
    title: "Keep track with statistics",
    description:
      "Measure community engagement with Pickups leaderboard, and celebrate your top community members!",
    icon: BarChart2,
  },
];