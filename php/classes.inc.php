<?php
class Vegetable
{
    public $edible;
    public $color;

    public function Vegetable($edible, $color="green") {
        $this->edible = $edible;
        $this->color = $color;
    }

    public function is_edible() {
        return $this->edible;
    }

    public function what_color() {
        return $this->color;
    }
}

class Spinach extends Vegetable
{

    public $cooked = false;

    public function Spinach() {
        $this->Vegetable(true, "green");
    }

    public function cook_it() {
        $this->cooked = true;
    }

    public function is_cooked() {
        return $this->cooked;
    }
}
?>