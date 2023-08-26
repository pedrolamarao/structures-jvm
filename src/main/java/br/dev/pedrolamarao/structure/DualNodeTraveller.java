// Copyright (C) 2023 Pedro Lamarão <pedro.lamarao@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package br.dev.pedrolamarao.structure;

import static java.util.Objects.requireNonNull;

public record DualNodeTraveller<T>(DualNode<T> root) implements TraversableUniLinear<T>
{
    public static <U> DualNodeTraveller<U> empty ()
    {
        return new DualNodeTraveller<>(null);
    }

    public static <U> DualNodeTraveller<U> of (DualNode<U> node)
    {
        requireNonNull(node);
        return new DualNodeTraveller<>(node);
    }

    public BiTraversal<T> forward ()
    {
        return root == null ? null : new DualNodeLinearTraversal<>(root);
    }
}
