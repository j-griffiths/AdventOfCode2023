if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    exit
fi

echo "Setting up day: $1";
DAY=$(printf "%02d" $1);
mkdir -p day$DAY
cp dayXX/dayXX.java day$DAY/day$DAY.java
sed -i "s/XX/$DAY/g" day$DAY/day$DAY.java
touch day$DAY/input.txt
